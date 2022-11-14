package com.example.springjpatest.jpa.services;

import com.example.springjpatest.jpa.dto.AuthorEntityDto;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    Page<AuthorEntityDto> list(@Nullable Pageable pageable);

    AuthorEntityDto update(@NotNull AuthorEntityDto authorDto);
}
