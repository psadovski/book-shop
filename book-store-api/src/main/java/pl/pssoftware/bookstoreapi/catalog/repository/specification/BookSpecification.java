package pl.pssoftware.bookstoreapi.catalog.repository.specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import pl.pssoftware.bookstoreapi.catalog.dto.BookFilterDTO;
import pl.pssoftware.bookstoreapi.catalog.entity.Author;
import pl.pssoftware.bookstoreapi.catalog.entity.Book;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BookSpecification {

    public static Specification<Book> filter(BookFilterDTO filter) {
        return (root, query, cb) -> {
            query.distinct(true);

            List<Predicate> predicates = new ArrayList<>();

            String title = filter.getTitle();
            if (StringUtils.isNotBlank(title)) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("title")),
                                "%" + title.toLowerCase(Locale.ROOT) + "%"
                        )
                );
            }

            if (filter.getCategory() != null) {
                predicates.add(
                        cb.equal(
                                root.get("category"),
                                filter.getCategory()
                        )
                );
            }

            String authorName = filter.getAuthorFirstName();
            String authorSurname = filter.getAuthorLastName();

            if (StringUtils.isNotBlank(authorName) || StringUtils.isNotBlank(authorSurname)) {
                Join<Book, Author> authorJoin = root.join("authors");
                if (StringUtils.isNotBlank(authorName)) {
                    predicates.add(
                            cb.like(
                                    cb.lower(authorJoin.get("name")),
                                    "%" + authorName.toLowerCase(Locale.ROOT) + "%"
                            )
                    );
                }

                if (StringUtils.isNotBlank(authorSurname)) {
                    predicates.add(
                            cb.like(
                                    cb.lower(authorJoin.get("surname")),
                                    "%" + authorSurname.toLowerCase(Locale.ROOT) + "%"
                            )
                    );
                }
            }

            BigDecimal minPrice = filter.getMinPrice();
            if (minPrice != null) {
                predicates.add(
                        cb.greaterThanOrEqualTo(root.get("price"), minPrice)
                );
            }

            BigDecimal maxPrice = filter.getMaxPrice();
            if (maxPrice != null) {
                predicates.add(
                        cb.lessThanOrEqualTo(root.get("price"), maxPrice)
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
