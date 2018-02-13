package kov.develop.mvc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.logging.FileHandler;

public class Datalogger {

    public static final java.util.logging.Logger DATA_LOGGER = java.util.logging.Logger.getLogger("dataLogger");

    private static final Logger logger = LoggerFactory.getLogger(Datalogger.class);

    static
    {
        try {
            DATA_LOGGER.addHandler(new FileHandler("%tdatachange.log"));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Datalogger couldn't find a logfile!");
        }
    }
}
