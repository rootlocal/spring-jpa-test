package com.example.springjpatest.web.controllers.rest;

import com.example.springjpatest.jpa.dto.BookEntityDto;
import com.example.springjpatest.jpa.services.BookService;
import com.example.springjpatest.web.exception.BadRequestException;
import com.example.springjpatest.web.exception.NotFoundException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookEntityDto> index() {
        return bookService.list();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookEntityDto> createBook(@RequestBody @NotNull @Valid BookEntityDto book, @NotNull HttpServletResponse response) throws BadRequestException {

        try {
            return new ResponseEntity<>(bookService.add(book), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

}
