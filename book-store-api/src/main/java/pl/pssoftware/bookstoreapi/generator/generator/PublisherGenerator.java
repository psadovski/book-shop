package pl.pssoftware.bookstoreapi.generator.generator;

import pl.pssoftware.bookstoreapi.generator.model.GeneratedPublisher;

import java.util.ArrayList;
import java.util.List;

public class PublisherGenerator {

    private static final List<String> PUBLISHERS = List.of(
            "O'Reilly",
            "Packt",
            "Apress",
            "Pearson",
            "Manning",
            "Helion",
            "PWN",
            "No Starch Press",
            "Pragmatic Bookshelf",
            "Wiley"
    );

    public List<GeneratedPublisher> generate(int count) {
        List<GeneratedPublisher> result = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            String name =
                    PUBLISHERS.get(i % PUBLISHERS.size()) + " " + i;

            result.add(new GeneratedPublisher(
                    (long) i,
                    name
            ));
        }

        return result;
    }

}
