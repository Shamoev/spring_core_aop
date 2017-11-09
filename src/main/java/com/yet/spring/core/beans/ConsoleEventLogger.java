package com.yet.spring.core.beans;

import com.yet.spring.core.loggers.Event;
import com.yet.spring.core.loggers.EventLogger;

public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event) {
        System.out.println(event);
    }
}
