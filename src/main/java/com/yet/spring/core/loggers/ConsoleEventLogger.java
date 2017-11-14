package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Event;
import com.yet.spring.core.loggers.EventLogger;

public class ConsoleEventLogger extends AbstractLogger {
    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}
