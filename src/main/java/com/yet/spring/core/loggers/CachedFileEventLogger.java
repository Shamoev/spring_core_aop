package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Event;

import java.util.ArrayList;
import java.util.List;

public class    CachedFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;

    public CachedFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<Event>(cacheSize);
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

    public void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCache(cache);
        }
    }
}
