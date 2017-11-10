package com.yet.spring.core;

import com.yet.spring.core.beans.Client;
import com.yet.spring.core.beans.Event;
import com.yet.spring.core.beans.EventType;
import com.yet.spring.core.loggers.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private Client client;

    private EventLogger defaultLogger;

    private Map<EventType, EventLogger> loggers;

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
        //ApplicationContext
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        // Child context
        // ApplicationContext child = new ClassPathXmlApplicationContext("child.xml", "parent.xml");

        // bean by class
        // App app = (App)context.getBean(App.class);

        App app = (App)context.getBean("app");

        Event event = context.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for user 1");

        event = context.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for user 2");

        event = context.getBean(Event.class);
        app.logEvent(null, event, "Some event for user 3");


        context.close();
    }

    void logEvent(EventType eventType, Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);

        EventLogger logger = loggers.get(eventType);
        if (logger == null) {
            logger = defaultLogger;
        }

        logger.logEvent(event);
    }
}
