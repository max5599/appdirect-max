package com.mct.appdirect.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
class ErrorLogger {

    private final Logger logger = LoggerFactory.getLogger(ErrorLogger.class);

    void logException(String message, Exception ex) {
        logger.error("Exception was thrown:", ex);
    }
}
