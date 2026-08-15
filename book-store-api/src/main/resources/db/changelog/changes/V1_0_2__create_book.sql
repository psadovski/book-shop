--liquibase formatted sql
--changeset psadowski:V1_0_2

CREATE SEQUENCE book_store.book_generator_sequence
    START WITH 1
    INCREMENT BY 50
    CACHE 50;

CREATE TABLE book_store.book
(
    id BIGINT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    isbn VARCHAR(13) NOT NULL UNIQUE,
    release_date DATE,
    release_place VARCHAR(100),
    language VARCHAR(20),
    page_count INTEGER,
    published_year INTEGER,
    category VARCHAR(30),
    price NUMERIC(10,2),
    rating NUMERIC(2,1),
    review_count INTEGER,
    cover_url VARCHAR(500),
    description TEXT,
    publisher_id BIGINT NOT NULL,
    stock INTEGER NOT NULL DEFAULT 0,
    available BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT fk_book_publisher
        FOREIGN KEY (publisher_id)
            REFERENCES book_store.publisher(id)
);

CREATE INDEX idx_book_title
    ON book_store.book(title);

CREATE INDEX idx_book_isbn
    ON book_store.book(isbn);

CREATE INDEX idx_book_publisher
    ON book_store.book(publisher_id);
