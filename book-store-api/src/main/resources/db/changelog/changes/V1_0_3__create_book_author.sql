--liquibase formatted sql
--changeset psadowski:V1_0_3

CREATE TABLE book_store.book_author
(
    book_id BIGINT NOT NULL,

    author_id BIGINT NOT NULL,

    PRIMARY KEY (book_id, author_id),

    CONSTRAINT fk_book_author_book
        FOREIGN KEY (book_id)
            REFERENCES book_store.book(id),

    CONSTRAINT fk_book_author_author
        FOREIGN KEY (author_id)
            REFERENCES book_store.author(id)
);