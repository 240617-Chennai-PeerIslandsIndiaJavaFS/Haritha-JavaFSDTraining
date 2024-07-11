package org.example;

import org.example.controller.Start;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Application starting...");

        try {
            Start start = new Start();
            start.startApplication();
            logger.info("Application started successfully.");
        } catch (Exception e) {
            logger.error("Error starting the application.", e);
        }
    }
}
