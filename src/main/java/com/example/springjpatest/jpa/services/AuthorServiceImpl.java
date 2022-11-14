package com.example.springjpatest.jpa.services;

import com.example.springjpatest.jpa.dto.AuthorEntityDto;
import com.example.springjpatest.jpa.entity.AuthorEntity;
import com.example.springjpatest.jpa.repository.AuthorEntityRepository;
import com.example.springjpatest.util.MapperUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorEntityRepository authorEntityRepository;

    @Autowired
    public AuthorServiceImpl(AuthorEntityRepository authorEntityRepository) {
        this.authorEntityRepository = authorEntityRepository;
    }

    @Override
    public AuthorEntityDto add(AuthorEntityDto author) {
        ModelMapper mapper = new ModelMapper();
        AuthorEntity authorEntity = mapper.map(author, AuthorEntity.class);

        return mapper.map(authorEntityRepository.save(authorEntity), AuthorEntityDto.class);
    }

    @Override
    public boolean isExits(String name) {
        return authorEntityRepository.existsByName(name);
    }

    @Override
    public boolean isExits(Long id) {
        return authorEntityRepository.existsById(id);
    }

    @Override
    public Optional<AuthorEntityDto> view(Long id) {
        Optional<AuthorEntity> authorEntity = authorEntityRepository.findById(id);

        if (authorEntity.isPresent()) {
            ModelMapper mapper = new ModelMapper();
            return Optional.of(mapper.map(authorEntity.get(), AuthorEntityDto.class));
        }

        return Optional.empty();
    }

    @Override
    public Optional<AuthorEntityDto> view(String name) {
        Optional<AuthorEntity> authorEntity = authorEntityRepository.findByName(name);

        if (authorEntity.isPresent()) {
            ModelMapper mapper = new ModelMapper();
            return Optional.of(mapper.map(authorEntity.get(), AuthorEntityDto.class));
        }

        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        Optional<AuthorEntity> authorEntity = authorEntityRepository.findById(id);
        authorEntity.ifPresent(authorEntityRepository::delete);
    }

    @Override
    public List<AuthorEntityDto> list() {
        List<AuthorEntity> authorEntities = authorEntityRepository.findAll();
        return MapperUtils.mapAll(authorEntities, AuthorEntityDto.class);
    }
}
