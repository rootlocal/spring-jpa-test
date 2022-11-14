package com.example.springjpatest.jpa.entity;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public enum BookTypeEnum {
    BOOK_TYPE_CLASSICS("Classics"),
    BOOK_TYPE_CRIME("Crime"),
    BOOK_TYPE_FANTASY("Fantasy"),
    BOOK_TYPE_ROMANCE("Romance"),
    BOOK_TYPE_BIOGRAPHY("Biography");

    private final String type;

    BookTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static @NotNull BookTypeEnum fromString(String type) throws IllegalArgumentException {

        return Arrays.stream(BookTypeEnum.values())
                .filter(v -> v.type.equals(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unknown type: " + type));
    }
}
