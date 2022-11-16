create table `t_book_author`
(
    book_id   BIGINT not null,
    author_id BIGINT not null,
    CONSTRAINT pk_t_book_author PRIMARY KEY (book_id, author_id),
    constraint fk_book_author_author_key
        foreign key (author_id) references `t_author` (id),
    constraint fk_book_author_book_key
        foreign key (book_id) references `t_book` (id)
);