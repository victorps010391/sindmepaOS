USE [cdi_ADMlog]
GO
/****** Object:  UserDefinedFunction [dbo].[FC_INT_ENG_RETORNA_PROXIMO_NUMERO_DEMANDA]    Script Date: 30/08/2019 18:40:18 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER OFF
GO

 --SELECT DBO.FC_INT_ENG_RETORNA_PROXIMO_NUMERO_DEMANDA()
ALTER FUNCTION [dbo].[FC_INT_ENG_RETORNA_PROXIMO_NUMERO_DEMANDA]() RETURNS INT
AS
BEGIN
  DECLARE @DT_PROC_ATU SMALLDATETIME,
          @NR_DEM_NEW INT

  SET @DT_PROC_ATU = cast(convert(char(10),getdate(),101) as smalldatetime)
  SET @NR_DEM_NEW = 0

  SET @NR_DEM_NEW = (SELECT CASE WHEN MAX(CONVERT(INT, (SUBSTRING(CONVERT(VARCHAR, RDE_NR_DEM), 1, LEN(RDE_NR_DEM) - 4)))) IS NULL
                                  THEN CONVERT(INT, '1' + CONVERT(VARCHAR, DATEPART(YEAR, @DT_PROC_ATU)))
                                  ELSE CONVERT(INT, CONVERT(VARCHAR, (MAX(CONVERT(INT, (SUBSTRING(CONVERT(VARCHAR, RDE_NR_DEM), 1, LEN(RDE_NR_DEM) - 4)))) + 1)) + CONVERT(VARCHAR, DATEPART(YEAR, @DT_PROC_ATU))) 
                             END AS NOVO_NR_PROC
                      FROM ENG_REGISTRO_DEMANDA
                      WHERE (SUBSTRING(CONVERT(VARCHAR, RDE_NR_DEM), LEN(RDE_NR_DEM) - 3, LEN(RDE_NR_DEM))) = CONVERT(VARCHAR, DATEPART(YEAR, @DT_PROC_ATU)))
  
  RETURN(@NR_DEM_NEW)                      
  
END; 
