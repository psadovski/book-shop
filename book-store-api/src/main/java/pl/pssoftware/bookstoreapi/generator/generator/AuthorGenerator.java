package pl.pssoftware.bookstoreapi.generator.generator;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import pl.pssoftware.bookstoreapi.generator.model.GeneratedAuthor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
public class AuthorGenerator {

    private final Faker faker = new Faker(Locale.ENGLISH);

    public List<GeneratedAuthor> generate(int count) {

        List<GeneratedAuthor> authors = new ArrayList<>();

        for (long id = 1; id <= count; id++) {

            LocalDate birthDate =
                    Instant.ofEpochMilli(
                                    faker.date()
                                            .birthday(30, 90)
                                            .getTime()
                            )
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();

            authors.add(
                    new GeneratedAuthor(
                            id,
                            faker.name().firstName(),
                            faker.name().lastName(),
                            birthDate
                    )
            );
        }

        return authors;
    }
}
