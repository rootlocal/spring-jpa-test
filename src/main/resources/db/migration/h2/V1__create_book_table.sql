CREATE TABLE `t_book`
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    name    VARCHAR(255)          NOT NULL,
    type    INT                   NOT NULL,
    version BIGINT,
    CONSTRAINT pk_t_book PRIMARY KEY (id)
);

ALTER TABLE `t_book`
    ADD CONSTRAINT `uc_t_book_name` UNIQUE (name);