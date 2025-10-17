package br.rafael.users.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {
    
    private static final Logger log = LoggerFactory.getLogger(LogUtils.class);

    public static void info(String message) {
        log.info(message);
    }

    public static void error(String message) {
        log.error(message);
    }

    public static void warn(String message) {
        log.warn(message);
    }

    public static void trace(String message) {
        log.trace(message);
    }

}
