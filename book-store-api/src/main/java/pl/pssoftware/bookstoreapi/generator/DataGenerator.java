package pl.pssoftware.bookstoreapi.generator;

import pl.pssoftware.bookstoreapi.generator.generator.AuthorGenerator;
import pl.pssoftware.bookstoreapi.generator.generator.BookGenerator;
import pl.pssoftware.bookstoreapi.generator.generator.PublisherGenerator;
import pl.pssoftware.bookstoreapi.generator.model.GeneratedAuthor;
import pl.pssoftware.bookstoreapi.generator.model.GeneratedBook;
import pl.pssoftware.bookstoreapi.generator.model.GeneratedPublisher;

import java.util.List;

public class DataGenerator {

    static void main(String[] args) throws Exception {

        SqlWriter writer = new SqlWriter(
                "src/main/resources/db/changelog/changes/V1_0_4__insert_demo_data.sql"
        );

        PublisherGenerator publisherGenerator = new PublisherGenerator();
        AuthorGenerator authorGenerator = new AuthorGenerator();
        BookGenerator bookGenerator = new BookGenerator();

        List<GeneratedPublisher> publishers = publisherGenerator.generate(200);

        List<GeneratedAuthor> authors = authorGenerator.generate(1000);

        List<GeneratedBook> books =
                bookGenerator.generate(5000, publishers, authors);

        writer.writePublishers(publishers);
        writer.writeAuthors(authors);
        writer.writeBooks(books);
        writer.writeBookAuthors(books);

        writer.finish();
    }

}