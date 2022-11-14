package com.example.springjpatest.web.controllers;

import com.example.springjpatest.jpa.dto.BookEntityDto;
import com.example.springjpatest.jpa.services.BookService;
import com.example.springjpatest.web.exception.BadRequestException;
import com.example.springjpatest.web.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    private static final Logger log = LoggerFactory.getLogger(BookController.class);
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
    public ResponseEntity<BookEntityDto> view(@PathVariable String id) throws NotFoundException, BadRequestException {

        try {
            Optional<BookEntityDto> optional = bookService.view(Long.decode(id));
            return new ResponseEntity<>(optional.orElseThrow(NotFoundException::new), HttpStatus.OK);
        } catch (NumberFormatException e) {
            log.error("NumberFormatException: {}" + e.getMessage());
            StringBuilder builder = new StringBuilder();
            builder.append("NumberFormatException (Bad Request)\n");
            builder.append(String.format("id: %s type not Long", id));
            throw new BadRequestException(builder.toString());
        }

    }
}
