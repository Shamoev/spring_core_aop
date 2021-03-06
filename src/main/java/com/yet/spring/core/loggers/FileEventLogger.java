package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileEventLogger extends AbstractLogger {

    private File file;

    private String fileName;

    public FileEventLogger(String filename) {
        this.fileName = filename;
    }

    public void init() {
        file = new File(fileName);
        if (file.exists() && !file.canWrite()) {
            throw new IllegalArgumentException("Can't write to file " + fileName);
        } else if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                throw new IllegalArgumentException("Can't create file", e);
            }

        }
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString(), Charset.forName("ISO-8859-1"), true);
            FileUtils.writeStringToFile(file, "\n", Charset.forName("ISO-8859-1"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
