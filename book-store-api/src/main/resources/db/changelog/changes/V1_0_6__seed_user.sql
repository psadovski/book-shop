--liquibase formatted sql
--changeset psadowski:V1_0_6

INSERT INTO book_store.user (id,
                              email,
                              password_hash,
                              first_name,
                              last_name,
                              phone_number,
                              status,
                              created_at)
VALUES (nextval('book_store.user_generator_sequence'),
        'mail@mail.pl',
        '$2a$10$a96lDqCkklfNS4g07PoytelPGLwPJvn/xkZC9pOCh5QmyYsf5E866',
        'Test',
        'User',
        '123456789',
        'ACTIVE',
        CURRENT_TIMESTAMP);

-- password123

-------------------------------------------------------------

INSERT INTO book_store.user_role (user_id,
                                  role_id)
SELECT u.id,
       r.id
FROM book_store.user u
         JOIN book_store.role r
              ON r.name = 'ROLE_USER'
WHERE u.email = 'test@test.pl';