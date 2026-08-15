package pl.pssoftware.bookstoreapi.generator.generator;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import pl.pssoftware.bookstoreapi.catalog.entity.BookCategory;
import pl.pssoftware.bookstoreapi.generator.model.GeneratedAuthor;
import pl.pssoftware.bookstoreapi.generator.model.GeneratedBook;
import pl.pssoftware.bookstoreapi.generator.model.GeneratedPublisher;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
public class BookGenerator {

    private final Faker faker = new Faker(Locale.ENGLISH);
    private final BookDescriptionGenerator descriptionGenerator = new BookDescriptionGenerator();

    public List<GeneratedBook> generate(
            int count,
            List<GeneratedPublisher> publishers,
            List<GeneratedAuthor> authors
    ) {

        List<GeneratedBook> books = new ArrayList<>();

        for (long id = 1; id <= count; id++) {

            LocalDate releaseDate =
                    Instant.ofEpochMilli(
                                    faker.date()
                                            .birthday(1, 20)
                                            .getTime()
                            )
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();

            BookCategory category = randomCategory();
            books.add(
                    GeneratedBook.builder()
                            .id(id)
                            .title(generateTitle())
                            .isbn(IsbnGenerator.generate())
                            .releaseDate(releaseDate)
                            .publishedYear(releaseDate.getYear())
                            .releasePlace(faker.address().city())
                            .language(randomLanguage())
                            .pageCount(randomInt(150, 900))
                            .price(randomPrice())
                            .rating(randomRating())
                            .reviewCount(randomInt(0, 5000))
                            .stock(randomInt(0, 200))
                            .available(true)
                            .category(category)
                            .coverUrl(generateCoverUrl(id))
                            .description(descriptionGenerator.generate(category))
                            .publisher(randomPublisher(publishers))
                            .authors(randomAuthors(authors))
                            .build()
            );
        }

        return books;
    }

    private String generateTitle() {

        String[] prefix = {
                "Mastering",
                "Learning",
                "Advanced",
                "Modern",
                "Practical",
                "Professional",
                "Clean",
                "Effective",
                "Beginning"
        };

        String[] topic = {
                "Java",
                "Spring Boot",
                "Hibernate",
                "Angular",
                "Docker",
                "Kubernetes",
                "Kafka",
                "Microservices",
                "SQL",
                "REST APIs",
                "Design Patterns",
                "Cloud Native"
        };
        return prefix[randomInt(0, prefix.length - 1)]
                + " "
                + topic[randomInt(0, topic.length - 1)];
    }

    private GeneratedPublisher randomPublisher(List<GeneratedPublisher> publishers) {
        return publishers.get(
                ThreadLocalRandom.current().nextInt(publishers.size())
        );
    }

    private Set<GeneratedAuthor> randomAuthors(List<GeneratedAuthor> authors) {
        int count = randomInt(1, 3);
        Set<GeneratedAuthor> result = new HashSet<>();
        while (result.size() < count) {
            result.add(
                    authors.get(
                            ThreadLocalRandom.current().nextInt(authors.size())
                    )
            );
        }

        return result;
    }

    private BookCategory randomCategory() {
        BookCategory[] categories = BookCategory.values();
        return categories[
                ThreadLocalRandom.current().nextInt(categories.length)
                ];
    }

    private String randomLanguage() {
        List<String> languages = List.of(
                "EN",
                "PL",
                "DE",
                "FR"
        );

        return languages.get(
                ThreadLocalRandom.current().nextInt(languages.size())
        );
    }

    private BigDecimal randomPrice() {
        return BigDecimal.valueOf(
                        ThreadLocalRandom.current()
                                .nextDouble(29, 299)
                )
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal randomRating() {
        return BigDecimal.valueOf(
                        ThreadLocalRandom.current()
                                .nextDouble(2.5, 5.0)
                )
                .setScale(1, RoundingMode.HALF_UP);
    }

    private String generateCoverUrl(Long id) {
        return "https://picsum.photos/300/500?random=" + id;
    }

    private int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}