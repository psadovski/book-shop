package pl.pssoftware.bookstoreapi.generator.model;

import lombok.Builder;
import lombok.Getter;
import pl.pssoftware.bookstoreapi.catalog.entity.BookCategory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Builder
public class GeneratedBook {

    private Long id;

    private String title;

    private String isbn;

    private LocalDate releaseDate;

    private String releasePlace;

    private String language;

    private Integer pageCount;

    private Integer publishedYear;

    private BookCategory category;

    private BigDecimal price;

    private BigDecimal rating;

    private Integer reviewCount;

    private Integer stock;

    private Boolean available;

    private String coverUrl;

    private String description;

    private GeneratedPublisher publisher;

    private Set<GeneratedAuthor> authors;

}
