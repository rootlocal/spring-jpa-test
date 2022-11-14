package com.example.springjpatest.web.controllers;

import com.example.springjpatest.jpa.dto.BookEntityDto;
import com.example.springjpatest.jpa.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookEntityDto> index() {
        return bookService.list();
    }

    @GetMapping("{id}")
    public BookEntityDto view(@PathVariable Long id) {
        Optional<BookEntityDto> optional = bookService.view(id);
        return optional.orElse(null);
    }
}
