package com.yet.spring.core;

import com.yet.spring.core.aspects.StatisticsAspect;
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

    private String startupMessage;

    private StatisticsAspect statisticsAspect;

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
                "spring.xml", "loggers.xml", "aspects.xml", "db.xml");

        App app = (App)context.getBean("app");

        System.out.println(app.startupMessage);

        Client client = context.getBean(Client.class);
        System.out.println("Client says: " + client.getGreeting());

        Event event = context.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for user 1");

        event = context.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for user 2");

        event = context.getBean(Event.class);
        app.logEvent(null, event, "Some event for user 3");

        app.outputLoggingCounter();

        context.close();
    }

    public StatisticsAspect getStatisticsAspect() {
        return statisticsAspect;
    }

    public void setStatisticsAspect(StatisticsAspect statisticsAspect) {
        this.statisticsAspect = statisticsAspect;
    }

    public EventLogger getDefaultLogger() {
        return defaultLogger;
    }

    public void setDefaultLogger(EventLogger defaultLogger) {
        this.defaultLogger = defaultLogger;
    }

    public String getStartupMessage() {

        return startupMessage;
    }

    public void setStartupMessage(String startupMessage) {
        this.startupMessage = startupMessage;
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

    private void outputLoggingCounter() {
        if (statisticsAspect != null) {
            System.out.println("Loggers statistics. Number of calls: ");
            for (Map.Entry<Class<?>, Integer> entry: statisticsAspect.getCounter().entrySet()) {
                System.out.println("    " + entry.getKey().getSimpleName() + ": " + entry.getValue());
            }
        }
    }
}
