package com.yet.spring.core.util;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

public class Listener implements ApplicationListener {
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println(applicationEvent.getClass().getSimpleName());
    }
}
