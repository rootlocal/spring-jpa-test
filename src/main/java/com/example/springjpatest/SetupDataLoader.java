package com.example.springjpatest;

import com.example.springjpatest.jpa.dto.BookEntityDto;
import com.example.springjpatest.jpa.entity.BookTypeEnum;
import com.example.springjpatest.jpa.services.BookService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final static Logger log = LoggerFactory.getLogger(SetupDataLoader.class);
    private final BookService bookService;
    boolean alreadySetup = false;

    @Autowired
    public SetupDataLoader(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void onApplicationEvent(@NotNull ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        log.warn("Setup Data Loader");

        BookEntityDto book1 = new BookEntityDto("test1", BookTypeEnum.TYPE_1);
        addBook(book1);
        BookEntityDto book2 = new BookEntityDto("test2", BookTypeEnum.TYPE_2);
        addBook(book2);
        BookEntityDto book3 = new BookEntityDto("test3", BookTypeEnum.TYPE_3);
        addBook(book3);
        BookEntityDto book4 = new BookEntityDto("test4", BookTypeEnum.TYPE_1);
        addBook(book4);

        alreadySetup = true;
    }

    private void addBook(@NotNull BookEntityDto book) {
        if (!bookService.isExits(book.getName())) {
            bookService.add(book);
        }
    }

}
