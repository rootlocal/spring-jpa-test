CREATE TABLE `t_author`
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_t_author PRIMARY KEY (id)
);

ALTER TABLE `t_author`
    ADD CONSTRAINT `uc_t_author_name` UNIQUE (name);