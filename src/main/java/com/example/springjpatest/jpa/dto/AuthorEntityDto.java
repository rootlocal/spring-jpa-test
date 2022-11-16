package com.example.springjpatest.jpa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.*;

/**
 * A DTO for the {@link com.example.springjpatest.jpa.entity.AuthorEntity} entity
 */
@Data
public class AuthorEntityDto implements Serializable {
    private static final long serialVersionUID = -2125291097631942898L;

    @JsonProperty("authorId")
    private Long id;
    @JsonProperty("firstName")
    private String name;
    @JsonIgnore
    private Collection<BookEntityDto> books = new ArrayList<>();

    public AuthorEntityDto() {
    }

    public AuthorEntityDto(String name) {
        this.name = name;
    }
}