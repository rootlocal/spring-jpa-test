package com.example.springjpatest.jpa.dto;

import com.example.springjpatest.jpa.entity.BookTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.springjpatest.jpa.entity.BookEntity} entity
 */
@Data
@ToString
@Getter
@Setter
public class BookEntityDto implements Serializable {

    private static final long serialVersionUID = 2406245506155274552L;
    @JsonProperty("book-id")
    private Long id;
    @JsonProperty("book-name")
    private String name;
    @JsonProperty("book-type")
    private BookTypeEnum type;

    public BookEntityDto() {
    }

    public BookEntityDto(Long id, String name, BookTypeEnum type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public BookEntityDto(String name, BookTypeEnum type) {
        this.name = name;
        this.type = type;
    }

}