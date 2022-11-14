package com.example.springjpatest.events;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class NewBookListener implements ApplicationListener<NewBookEvent> {
    private static final Logger log = LoggerFactory.getLogger(NewBookListener.class);

    @Override
    public void onApplicationEvent(@NotNull NewBookEvent event) {
        log.warn("NEW Book: {}", event.getBook().getName());
    }
}