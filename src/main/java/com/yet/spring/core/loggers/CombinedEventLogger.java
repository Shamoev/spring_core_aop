package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Event;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

@Component
public class CombinedEventLogger implements EventLogger {

    @Resource(name = "combinedLoggers")
    private Collection<EventLogger> loggers;


    public void logEvent(Event event) {
        for (EventLogger eventLogger : loggers) {
            eventLogger.    logEvent(event);
        }
    }
}
