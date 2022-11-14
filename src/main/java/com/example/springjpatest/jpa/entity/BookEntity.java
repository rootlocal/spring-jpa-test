package com.example.springjpatest.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "t_book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "type", nullable = false)
    private BookTypeEnum type;

    @Version
    @Column
    private Long version;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_book_author",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"),
            foreignKey = @ForeignKey(name = "fk_book_author_key")
    )
    private Set<AuthorEntity> authors = new HashSet<>();

    public BookEntity() {
    }


}