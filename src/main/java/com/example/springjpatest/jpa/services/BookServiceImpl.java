package com.example.springjpatest.jpa.services;

import com.example.springjpatest.jpa.dto.BookEntityDto;
import com.example.springjpatest.jpa.entity.BookEntity;
import com.example.springjpatest.jpa.repository.BookEntityRepository;
import com.example.springjpatest.util.MapperUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookEntityRepository bookEntityRepository;

    @Autowired
    public BookServiceImpl(BookEntityRepository bookEntityRepository) {
        this.bookEntityRepository = bookEntityRepository;
    }

    @Override
    public BookEntityDto add(BookEntityDto book) {
        ModelMapper mapper = new ModelMapper();
        BookEntity bookEntity = mapper.map(book, BookEntity.class);
        BookEntity newBook = bookEntityRepository.save(bookEntity);

        return mapper.map(newBook, BookEntityDto.class);
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
        return bookEntityRepository
                .findById(id)
                .map(bookEntity -> MapperUtils.map(bookEntity, BookEntityDto.class));
    }

    @Override
    public Optional<BookEntityDto> view(String name) {
        return bookEntityRepository
                .findByName(name)
                .map(bookEntity -> MapperUtils.map(bookEntity, BookEntityDto.class));
    }

    @Override
    public void delete(Long id) {
        Optional<BookEntity> bookEntity = bookEntityRepository.findById(id);
        bookEntity.ifPresent(bookEntityRepository::delete);
    }

    @Override
    public List<BookEntityDto> list() {
        List<BookEntity> list = bookEntityRepository.findAll();
        return MapperUtils.mapAll(list, BookEntityDto.class);
    }

    @Override
    public Page<BookEntityDto> list(@Nullable Pageable pageable) {
        if (pageable == null) {
            pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));
        }

        Page<BookEntity> entities = bookEntityRepository.findByOrderByIdAsc(pageable);

        return MapperUtils.mapEntityPageIntoDtoPage(entities, BookEntityDto.class);
    }

    @Transactional
    @Override
    public BookEntityDto update(@NotNull BookEntityDto bookDto) {
        Optional<BookEntity> bookEntityOptional = bookEntityRepository.findById(bookDto.getId());

        if (bookEntityOptional.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            TypeMap<BookEntityDto, BookEntity> propertyMapper = modelMapper.createTypeMap(bookDto, BookEntity.class);
            propertyMapper.addMappings(mapper -> mapper.skip(BookEntity::setId));
            propertyMapper.setProvider(p -> bookEntityOptional.get());
            BookEntity book = modelMapper.map(bookDto, BookEntity.class);

            return modelMapper.map(bookEntityRepository.save(book), BookEntityDto.class);
        }

        return null;
    }
}