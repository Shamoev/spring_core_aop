package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Event;
import com.yet.spring.core.loggers.EventLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event) {
        System.out.println(event);
    }
}
