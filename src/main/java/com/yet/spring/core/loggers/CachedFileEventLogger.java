package com.yet.spring.core.loggers;

import java.util.ArrayList;
import java.util.List;

public class CachedFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;

    public CachedFileEventLogger(String fileNmae, int cacheSize) {
        super(fileNmae);
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
