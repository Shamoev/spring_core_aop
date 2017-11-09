package com.yet.spring.core;

import com.yet.spring.core.beans.Client;
import com.yet.spring.core.loggers.Event;
import com.yet.spring.core.loggers.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    Client client;
    EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        //ApplicationContext
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        // bean by class
        // App app = (App)context.getBean(App.class);

        App app = (App)context.getBean("app");

        Event event = context.getBean(Event.class);
        app.logEvent(event, "Some event for user 1");

        event = context.getBean(Event.class);
        app.logEvent(event, "Some event for user 2");

        context.close();
    }

    void logEvent(Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
    }
}
