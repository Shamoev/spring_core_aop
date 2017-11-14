package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component
public class CachedFileEventLogger extends FileEventLogger {

    // Use system property cache.size or 5 if property is not set
    @Value("${cache.size:5}")

    private int cacheSize;

    public CachedFileEventLogger() {
    }

    private List<Event> cache;

    public CachedFileEventLogger(String filename) {
        super(filename);
    }

    public CachedFileEventLogger(String fileNmae, int cacheSize) {
        super(fileNmae);
        this.cacheSize = cacheSize;
    }

    @PostConstruct
    public void initCache() {
        this.cache = new ArrayList<Event>(cacheSize);
    }

    @PreDestroy
    public void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCache(cache);
        }
    }

    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() == cacheSize) {
            writeEventsFromCache(cache);
            cache.clear();
        }
    }

    public void writeEventsFromCache(List<Event> cache) {
        for (Event event : cache) {
            super.logEvent(event);
        }
    }



}
