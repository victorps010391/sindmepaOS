<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of DB
 *
 * @author DELL
 */
require_once 'config.php';

class DB {

    private static $instance;

    public static function getInstance() {
        if (!isset(self::$instance)) {

            try {
                self::$instance = new PDO('pgsql:host=' . host . '; port=' . port . '; dbname=' . base . '; user=' . user . '; password=' . pass);

                self::$instance->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
                self::$instance->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_OBJ);
            } catch (Exception $e) {
                echo $e->getMessage();
            }
        }

        return self::$instance;
    }

    public static function prepare($sql) {
        return self::getInstance()->prepare($sql);
    }

}
