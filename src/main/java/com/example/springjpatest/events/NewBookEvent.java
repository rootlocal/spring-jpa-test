package com.example.springjpatest.events;

import com.example.springjpatest.jpa.dto.BookEntityDto;
import org.springframework.context.ApplicationEvent;

public class NewBookEvent extends ApplicationEvent {
    private final BookEntityDto book;

    public NewBookEvent(BookEntityDto book) {
        super(book);
        this.book = book;
    }

    public BookEntityDto getBook() {
        return book;
    }
}
