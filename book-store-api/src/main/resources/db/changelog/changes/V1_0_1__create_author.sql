--liquibase formatted sql
--changeset psadowski:V1_0_1

CREATE SEQUENCE book_store.author_generator_sequence
    START WITH 1
    INCREMENT BY 50
    CACHE 50;

CREATE TABLE book_store.author
(
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    date_of_birth DATE
);

CREATE INDEX idx_author_name
    ON book_store.author(name);

CREATE INDEX idx_author_surname
    ON book_store.author(surname);