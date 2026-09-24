--liquibase formatted sql
--changeset psadowski:V1_0_5

CREATE SEQUENCE book_store.role_generator_sequence
    START WITH 1
    INCREMENT BY 50;

CREATE TABLE book_store.role
(
    id   BIGINT      NOT NULL,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (id),
    CONSTRAINT uk_role_name UNIQUE (name)
);

-- -----------------------------------------------

CREATE SEQUENCE book_store.user_generator_sequence
    START WITH 1
    INCREMENT BY 50;

CREATE TABLE book_store.user
(
    id            BIGINT       NOT NULL,
    email         VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    first_name    VARCHAR(100) NOT NULL,
    last_name     VARCHAR(100) NOT NULL,
    phone_number  VARCHAR(30),
    status        VARCHAR(20)  NOT NULL,
    created_at    TIMESTAMP    NOT NULL,

    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uk_users_email UNIQUE (email)
);

-- -----------------------------------------------

CREATE TABLE book_store.user_role
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,

    CONSTRAINT pk_user_role
        PRIMARY KEY (user_id, role_id),

    CONSTRAINT fk_user_role_user
        FOREIGN KEY (user_id)
            REFERENCES book_store.user (id),

    CONSTRAINT fk_user_role_role
        FOREIGN KEY (role_id)
            REFERENCES book_store.role (id)
);


-- -----------------------------------------------

INSERT INTO book_store.role (id, name)
VALUES (nextval('book_store.role_generator_sequence'), 'ROLE_USER'),
       (nextval('book_store.role_generator_sequence'), 'ROLE_ADMIN');