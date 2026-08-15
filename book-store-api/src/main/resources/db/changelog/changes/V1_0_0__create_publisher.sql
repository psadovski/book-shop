--liquibase formatted sql
--changeset psadowski:V1_0_0

CREATE SEQUENCE book_store.publisher_generator_sequence
    START WITH 1
    INCREMENT BY 50
    CACHE 50;

CREATE TABLE book_store.publisher
(
    id BIGINT PRIMARY KEY,
    name VARCHAR(200) NOT NULL
);

CREATE INDEX idx_publisher_name
    ON book_store.publisher(name);