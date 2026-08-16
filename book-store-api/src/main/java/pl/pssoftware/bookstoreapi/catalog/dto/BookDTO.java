package pl.pssoftware.bookstoreapi.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pssoftware.bookstoreapi.catalog.entity.BookCategory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long id;
    private String title;
    private String isbn;
    private LocalDate releaseDate;
    private String releasePlace;
    private String language;
    private Integer pageCount;
    private BigDecimal price;
    private BookCategory category;
    private String description;
    private String coverUrl;
    private BigDecimal rating;
    private Integer reviewCount;
    private Integer publishedYear;
    private Integer stock;
    private Boolean available;
    private PublisherDTO publisher;
    private Set<AuthorDTO> authors;
}
