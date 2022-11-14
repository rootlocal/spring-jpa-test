package com.example.springjpatest.jpa.services;

import com.example.springjpatest.jpa.dto.BookEntityDto;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntityDto add(BookEntityDto book);

    boolean isExits(String name);

    boolean isExits(Long id);

    Optional<BookEntityDto> view(Long id);

    void delete(Long id);

    List<BookEntityDto> list();

    Page<BookEntityDto> list(@Nullable Pageable pageable);

    BookEntityDto update(BookEntityDto book);
}
