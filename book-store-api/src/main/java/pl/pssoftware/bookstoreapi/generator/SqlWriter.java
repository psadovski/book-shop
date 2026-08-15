package pl.pssoftware.bookstoreapi.generator;

import pl.pssoftware.bookstoreapi.generator.model.GeneratedAuthor;
import pl.pssoftware.bookstoreapi.generator.model.GeneratedBook;
import pl.pssoftware.bookstoreapi.generator.model.GeneratedPublisher;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SqlWriter {

    private final BufferedWriter writer;

    public SqlWriter(String outputFile) throws IOException {
        Path path = Path.of(outputFile);
        if (path.getParent() != null) {
            Files.createDirectories(path.getParent());
        }
        writer = Files.newBufferedWriter(path);
        writer.write("--liquibase formatted sql");
        writer.newLine();
        writer.newLine();
        writer.write("--changeset psadowski:V1_0_4");
        writer.newLine();
        writer.newLine();
    }

    public void writePublishers(List<GeneratedPublisher> publishers) throws IOException {
        for (GeneratedPublisher publisher : publishers) {
            writer.write("""
                    INSERT INTO book_store.publisher (id, name)
                    VALUES (%d, '%s');
                    """
                    .formatted(
                            publisher.getId(),
                            escape(publisher.getName())
                    ));
            writer.newLine();
        }
    }

    public void writeAuthors(List<GeneratedAuthor> authors) throws IOException {
        for (GeneratedAuthor author : authors) {
            writer.write("""
                    INSERT INTO book_store.author
                        (id, name, surname, date_of_birth)
                    VALUES (%d, '%s', '%s', '%s');
                    """
                    .formatted(
                            author.getId(),
                            escape(author.getName()),
                            escape(author.getSurname()),
                            author.getBirthDate()
                    ));
            writer.newLine();
        }
    }

    public void writeBooks(List<GeneratedBook> books) throws IOException {
        for (GeneratedBook book : books) {
            writer.write("""
                    INSERT INTO book_store.book
                        (
                            id,
                            title,
                            isbn,
                            release_date,
                            release_place,
                            language,
                            page_count,
                            published_year,
                            category,
                            price,
                            rating,
                            review_count,
                            stock,
                            available,
                            cover_url,
                            description,
                            publisher_id
                        )
                    VALUES
                        (
                            %d,
                            '%s',
                            '%s',
                            '%s',
                            '%s',
                            '%s',
                            %d,
                            %d,
                            '%s',
                            %s,
                            %s,
                            %d,
                            %d,
                            %s,
                            '%s',
                            '%s',
                            %d
                        );
                    """
                    .formatted(
                            book.getId(),
                            escape(book.getTitle()),
                            escape(book.getIsbn()),
                            book.getReleaseDate(),
                            escape(book.getReleasePlace()),
                            escape(book.getLanguage()),
                            book.getPageCount(),
                            book.getPublishedYear(),
                            book.getCategory().name(),
                            book.getPrice(),
                            book.getRating(),
                            book.getReviewCount(),
                            book.getStock(),
                            book.getAvailable(),
                            escape(book.getCoverUrl()),
                            escape(book.getDescription()),
                            book.getPublisher().getId()
                    ));
            writer.newLine();
        }
    }

    public void writeBookAuthors(List<GeneratedBook> books) throws IOException {
        for (GeneratedBook book : books) {
            for (GeneratedAuthor author : book.getAuthors()) {
                writer.write("""
                        INSERT INTO book_store.book_author
                            (book_id, author_id)
                        VALUES (%d, %d);
                        """
                        .formatted(
                                book.getId(),
                                author.getId()
                        ));

                writer.newLine();
            }
        }
    }

    public void finish() throws IOException {
        writer.newLine();
        writer.write("""
                SELECT setval(
                    'book_store.publisher_sequence',
                    (SELECT MAX(id) FROM book_store.publisher)
                );
                """);

        writer.newLine();
        writer.write("""
                SELECT setval(
                    'book_store.author_sequence',
                    (SELECT MAX(id) FROM book_store.author)
                );
                """);

        writer.newLine();
        writer.write("""
                SELECT setval(
                    'book_store.book_sequence',
                    (SELECT MAX(id) FROM book_store.book)
                );
                """);

        writer.newLine();
        writer.close();
    }

    private String escape(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("'", "''");
    }
}
