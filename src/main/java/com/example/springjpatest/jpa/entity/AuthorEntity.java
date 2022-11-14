package com.example.springjpatest.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "t_author")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(mappedBy = "authors")
    private Set<BookEntity> books = new HashSet<>();

    public AuthorEntity() {
    }
}

