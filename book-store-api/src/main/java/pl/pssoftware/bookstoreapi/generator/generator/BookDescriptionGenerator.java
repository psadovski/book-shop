package pl.pssoftware.bookstoreapi.generator.generator;

import pl.pssoftware.bookstoreapi.catalog.entity.BookCategory;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BookDescriptionGenerator {

    private static final List<String> JAVA_TOPICS = List.of(
            "modern Java development",
            "object-oriented programming",
            "Java concurrency",
            "collections and streams",
            "clean code and best practices",
            "Java performance optimization"
    );

    private static final List<String> SPRING_TOPICS = List.of(
            "Spring Boot application development",
            "Spring Security",
            "Spring Data JPA",
            "REST API development",
            "microservices with Spring",
            "production-ready Spring applications"
    );

    private static final List<String> DATABASE_TOPICS = List.of(
            "relational database design",
            "SQL optimization",
            "database indexing",
            "transaction management",
            "PostgreSQL",
            "database performance"
    );

    private static final List<String> CLOUD_TOPICS = List.of(
            "cloud-native application development",
            "distributed systems",
            "scalable cloud applications",
            "cloud infrastructure",
            "containerized applications",
            "modern cloud architecture"
    );

    private static final List<String> DEVOPS_TOPICS = List.of(
            "Docker and containerization",
            "Kubernetes",
            "CI/CD pipelines",
            "infrastructure automation",
            "deployment strategies",
            "DevOps best practices"
    );

    private static final List<String> AI_TOPICS = List.of(
            "machine learning",
            "generative AI",
            "large language models",
            "AI application development",
            "vector databases",
            "AI-powered software"
    );

    private static final List<String> WEB_TOPICS = List.of(
            "modern web application development",
            "frontend architecture",
            "REST APIs",
            "TypeScript",
            "Angular applications",
            "scalable web applications"
    );

    private static final List<String> SECURITY_TOPICS = List.of(
            "application security",
            "OAuth 2.0 and OpenID Connect",
            "JWT authentication",
            "Spring Security",
            "secure REST APIs",
            "identity and access management"
    );

    private static final List<String> GENERAL_TOPICS = List.of(
            "software architecture",
            "design patterns",
            "clean architecture",
            "distributed systems",
            "enterprise software development",
            "software engineering best practices"
    );

    public String generate(BookCategory category) {
        List<String> topics = getTopics(category);
        String topic1 = random(topics);
        String topic2 = randomDifferent(topics, topic1);

        return """
                This book provides a practical introduction to %s and %s.
                It combines fundamental concepts with real-world examples
                and practical techniques used in modern software development.
                Readers will learn how to design reliable solutions,
                avoid common mistakes and apply proven best practices
                in professional software projects.
                """
                .formatted(topic1, topic2)
                .replaceAll("\\s+", " ")
                .trim();
    }

    private List<String> getTopics(BookCategory category) {
        return switch (category) {
            case JAVA -> JAVA_TOPICS;
            case SPRING -> SPRING_TOPICS;
            case DATABASE -> DATABASE_TOPICS;
            case CLOUD -> CLOUD_TOPICS;
            case DEVOPS -> DEVOPS_TOPICS;
            case AI -> AI_TOPICS;
            case WEB -> WEB_TOPICS;
            case SECURITY -> SECURITY_TOPICS;
            default -> GENERAL_TOPICS;
        };
    }

    private String random(List<String> values) {
        return values.get(
                ThreadLocalRandom.current().nextInt(values.size())
        );
    }

    private String randomDifferent(
            List<String> values,
            String first) {
        String result;
        do {
            result = random(values);
        } while (result.equals(first));

        return result;
    }
}
