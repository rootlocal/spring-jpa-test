package com.example.springjpatest.events;

import com.example.springjpatest.jpa.dto.AuthorEntityDto;
import org.springframework.context.ApplicationEvent;

public class NewAuthorEvent extends ApplicationEvent {
    private final AuthorEntityDto author;

    public NewAuthorEvent(AuthorEntityDto author) {
        super(author);
        this.author = author;
    }

    public AuthorEntityDto getAuthor() {
        return author;
    }
}
