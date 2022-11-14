package com.example.springjpatest.jpa.services;

import com.example.springjpatest.jpa.dto.BookEntityDto;
import com.example.springjpatest.jpa.entity.BookEntity;
import com.example.springjpatest.jpa.repository.BookEntityRepository;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookEntityRepository bookEntityRepository;

    @Autowired
    public BookServiceImpl(BookEntityRepository bookEntityRepository) {
        this.bookEntityRepository = bookEntityRepository;
    }

    @Override
    public BookEntity add(BookEntityDto bookEntityDto) {
        ModelMapper mapper = new ModelMapper();
        BookEntity bookEntity = mapper.map(bookEntityDto, BookEntity.class);
        return bookEntityRepository.save(bookEntity);
    }

    @Override
    public boolean isExits(String name) {
        return bookEntityRepository.existsByName(name);
    }

    @Override
    public boolean isExits(Long id) {
        return bookEntityRepository.existsById(id);
    }


    @Override
    public Optional<BookEntityDto> view(Long id) {
        Optional<BookEntity> bookEntity = bookEntityRepository.findById(id);

        if (bookEntity.isPresent()) {
            ModelMapper mapper = new ModelMapper();
            return Optional.of(mapper.map(bookEntity.get(), BookEntityDto.class));
        }

        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        Optional<BookEntity> bookEntity = bookEntityRepository.findById(id);
        bookEntity.ifPresent(bookEntityRepository::delete);
    }

    @Override
    public List<BookEntityDto> list() {
        List<BookEntity> list = bookEntityRepository.findAll();
        return mapList(list, BookEntityDto.class);
    }

    <S, T> List<T> mapList(@NotNull List<S> source, Class<T> targetClass) {
        ModelMapper modelMapper = new ModelMapper();
        return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
    }
}