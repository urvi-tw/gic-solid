package com.tw.gic.bootcamp.solid.util;


public class Logger {

    private static Logger instance;

    private Logger() {

    }

    public static Logger instance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void info(String component, String message) {
        System.out.println("INFO: " + component + " : " + message);
    }

    public void error(String component, String message) {
        System.out.println("ERR: " + component + " : " + message);
    }
}
