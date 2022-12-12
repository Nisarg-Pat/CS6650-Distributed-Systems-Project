package com.example.cs6650.server.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class to log the requests and responses of client or server to a file
 */
public class Log {

    /**
     * Constructor for Log. (Does nothing)
     */
    public Log() {
    }

    /**
     * Logs without \n. Similar to System.out.print(s)
     *
     * @param s String to log
     */
    public static void log(String s) {
        s = getCurrentTime() + ": " + s;
        System.out.print(s);
    }

    /**
     * Logs with \n. Similar to Log.logln(s)
     *
     * @param s String to log
     */
    public static void logln(Object s) {
        s = getCurrentTime() + ": " + s;
        System.out.println(s);
    }

    /**
     * Gives currenttime in yyyy/MM/dd HH:mm:ss:SSS format
     *
     * @return The current time with millisecond precision
     */
    public static String getCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss:SSS");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
