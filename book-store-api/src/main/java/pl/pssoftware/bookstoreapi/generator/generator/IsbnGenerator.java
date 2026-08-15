package pl.pssoftware.bookstoreapi.generator.generator;

import java.util.concurrent.ThreadLocalRandom;

public final class IsbnGenerator {

    private IsbnGenerator() {
    }

    public static String generate() {

        StringBuilder isbn = new StringBuilder("978");

        while (isbn.length() < 13) {
            isbn.append(ThreadLocalRandom.current().nextInt(10));
        }
        return isbn.toString();
    }

}
