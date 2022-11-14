package com.example.springjpatest.jpa.repository;

import com.example.springjpatest.jpa.entity.AuthorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorEntityRepository extends JpaRepository<AuthorEntity, Long>, JpaSpecificationExecutor<AuthorEntity> {

    boolean existsByName(String name);

    Optional<AuthorEntity> findByName(String name);

    Page<AuthorEntity> findByOrderByIdAsc(Pageable pageable);
}