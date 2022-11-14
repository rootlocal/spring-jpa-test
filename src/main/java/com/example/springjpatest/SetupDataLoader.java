package com.example.springjpatest;

import com.example.springjpatest.jpa.dto.AuthorEntityDto;
import com.example.springjpatest.jpa.dto.BookEntityDto;

import static com.example.springjpatest.jpa.entity.BookTypeEnum.*;

import com.example.springjpatest.jpa.services.AuthorService;
import com.example.springjpatest.jpa.services.BookService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final static Logger log = LoggerFactory.getLogger(SetupDataLoader.class);
    private final BookService bookService;
    private final AuthorService authorService;
    boolean alreadySetup = false;

    @Autowired
    public SetupDataLoader(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public void onApplicationEvent(@NotNull ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        log.warn("Updated Data");

        List<AuthorEntityDto> authors = new ArrayList<>();
        authors.add(new AuthorEntityDto("author1"));
        authors.add(new AuthorEntityDto("author2"));
        authors.add(new AuthorEntityDto("author3"));
        authors.add(new AuthorEntityDto("author4"));
        authors.add(new AuthorEntityDto("author5"));
        authors.add(new AuthorEntityDto("author6"));
        authors.add(new AuthorEntityDto("author7"));
        authors.add(new AuthorEntityDto("author8"));
        authors.add(new AuthorEntityDto("author9"));
        authors.add(new AuthorEntityDto("author10"));
        authors.add(new AuthorEntityDto("author11"));
        authors.add(new AuthorEntityDto("author12"));

        for (AuthorEntityDto author : authors) {
            addAuthor(author);
        }

        Set<AuthorEntityDto> authorsList1 = new HashSet<>();
        Optional<AuthorEntityDto> author1 = authorService.view("author1");
        author1.ifPresent(authorsList1::add);
        Optional<AuthorEntityDto> author2 = authorService.view("author2");
        author2.ifPresent(authorsList1::add);
        Optional<AuthorEntityDto> author3 = authorService.view("author3");
        author3.ifPresent(authorsList1::add);

        Set<AuthorEntityDto> authorsList2 = new HashSet<>();
        Optional<AuthorEntityDto> author4 = authorService.view("author4");
        author4.ifPresent(authorsList2::add);
        Optional<AuthorEntityDto> author5 = authorService.view("author5");
        author5.ifPresent(authorsList2::add);

        BookEntityDto book1 = new BookEntityDto("test1", BOOK_TYPE_FANTASY);
        BookEntityDto book2 = new BookEntityDto("test2", BOOK_TYPE_ROMANCE);
        BookEntityDto book3 = new BookEntityDto("test3", BOOK_TYPE_CRIME);
        BookEntityDto book4 = new BookEntityDto("test4", BOOK_TYPE_BIOGRAPHY);
        BookEntityDto book5 = new BookEntityDto("test5", BOOK_TYPE_CLASSICS);
        BookEntityDto book6 = new BookEntityDto("test6", BOOK_TYPE_BIOGRAPHY);
        BookEntityDto book7 = new BookEntityDto("test7", BOOK_TYPE_ROMANCE);
        BookEntityDto book8 = new BookEntityDto("test8", BOOK_TYPE_FANTASY);
        BookEntityDto book9 = new BookEntityDto("test9", BOOK_TYPE_CRIME);

        addBook(book1, authorsList1);
        addBook(book2, authorsList2);
        addBook(book3, authorsList1);
        addBook(book4, authorsList2);
        addBook(book5, authorsList1);
        addBook(book6, authorsList2);
        addBook(book7, authorsList1);
        addBook(book8, authorsList2);
        addBook(book9, authorsList1);

        log.warn("End Updated Data");
        alreadySetup = true;
    }

    private void addBook(@NotNull BookEntityDto book, Set<AuthorEntityDto> authors) {
        if (!bookService.isExits(book.getName())) {
            log.warn("add book {}", book);
            BookEntityDto bookEntityDto = bookService.add(book);

            log.warn("set book authors {}", authors);
            bookEntityDto.setAuthors(authors);
            bookService.update(bookEntityDto);
        }
    }

    private void addAuthor(@NotNull AuthorEntityDto author) {
        if (!authorService.isExits(author.getName())) {
            log.warn("add author {}", author);
            authorService.add(author);
        }
    }

}
