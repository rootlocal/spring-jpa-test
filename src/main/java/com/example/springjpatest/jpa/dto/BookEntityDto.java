package com.example.springjpatest.jpa.dto;

import com.example.springjpatest.jpa.entity.BookTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.*;

/**
 * A DTO for the {@link com.example.springjpatest.jpa.entity.BookEntity} entity
 */
@ToString
public class BookEntityDto implements Serializable {

    private static final long serialVersionUID = 2406245506155274552L;
    @Getter
    @Setter
    @JsonProperty("id")
    private Long id;

    @Getter
    @Setter
    @NotEmpty
    @JsonProperty("name")
    private String name;

    @Getter
    @Setter
    @JsonIgnore
    private BookTypeEnum type;

    @JsonIgnore
    private String BookType;

    @Getter
    @Setter
    @JsonProperty("authors")
    private Collection<AuthorEntityDto> authors = new ArrayList<>();

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

    @JsonProperty("type")
    public String getBookType() {
        return type.getType();
    }

    public void setBookType(String type) {
        this.BookType = type;
        this.type = BookTypeEnum.fromString(type);
    }
}