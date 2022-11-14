package com.example.springjpatest.jpa.repository;

import com.example.springjpatest.jpa.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookEntityRepository extends JpaRepository<BookEntity, Long>, JpaSpecificationExecutor<BookEntity> {

    boolean existsByName(String name);

    Page<BookEntity> findByOrderByIdAsc(Pageable pageable);
}