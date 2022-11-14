package com.example.springjpatest.events;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class NewAuthorListener implements ApplicationListener<NewAuthorEvent> {
    private static final Logger log = LoggerFactory.getLogger(NewAuthorListener.class);

    @Override
    public void onApplicationEvent(@NotNull NewAuthorEvent event) {
        log.warn("NEW Author: {}", event.getAuthor().getName());
    }
}