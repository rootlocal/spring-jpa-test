package com.example.springjpatest.jpa.services;

import com.example.springjpatest.jpa.dto.AuthorEntityDto;
import com.example.springjpatest.jpa.dto.BookEntityDto;
import com.example.springjpatest.jpa.entity.BookEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    AuthorEntityDto add(AuthorEntityDto author);

    boolean isExits(String name);

    boolean isExits(Long id);

    Optional<AuthorEntityDto> view(Long id);
    Optional<AuthorEntityDto> view(String name);

    void delete(Long id);

    List<AuthorEntityDto> list();
}
