package com.example.springjpatest.jpa.services;

import com.example.springjpatest.jpa.dto.AuthorEntityDto;
import com.example.springjpatest.jpa.entity.AuthorEntity;
import com.example.springjpatest.jpa.repository.AuthorEntityRepository;
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

    public Page<AuthorEntityDto> list(@Nullable Pageable pageable) {
        if (pageable == null) {
            pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));
        }

        Page<AuthorEntity> entities = authorEntityRepository.findByOrderByIdAsc(pageable);

        return MapperUtils.mapEntityPageIntoDtoPage(entities, AuthorEntityDto.class);
    }

    @Transactional
    @Override
    public AuthorEntityDto update(@NotNull AuthorEntityDto authorDto) {
        Optional<AuthorEntity> authorEntityOptional = authorEntityRepository.findById(authorDto.getId());

        if (authorEntityOptional.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            TypeMap<AuthorEntityDto, AuthorEntity> propertyMapper = modelMapper.createTypeMap(authorDto, AuthorEntity.class);
            propertyMapper.addMappings(mapper -> mapper.skip(AuthorEntity::setId));
            propertyMapper.setProvider(p -> authorEntityOptional.get());
            AuthorEntity author = modelMapper.map(authorDto, AuthorEntity.class);

            return modelMapper.map(authorEntityRepository.save(author), AuthorEntityDto.class);
        }

        return null;
    }
}
