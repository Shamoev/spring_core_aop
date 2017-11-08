package com.yet.spring.core;

import com.yet.spring.core.beans.Client;
import com.yet.spring.core.beans.ConsoleEventLogger;
import com.yet.spring.core.beans.EventLogger;
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
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        // bean by class
        // App app = (App)context.getBean(App.class);

        App app = (App)context.getBean("app");

        app.logEvent("Some event for user 1");
    }

    void logEvent(String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        eventLogger.logEvent(message);
    }
}
