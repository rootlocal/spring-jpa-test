package com.example.springjpatest.jpa.services;

import com.example.springjpatest.jpa.dto.BookEntityDto;
import com.example.springjpatest.jpa.entity.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity add(BookEntityDto bookEntityDto);

    boolean isExits(String name);

    boolean isExits(Long id);

    Optional<BookEntityDto> view(Long id);

    void delete(Long id);

    List<BookEntityDto> list();
}
