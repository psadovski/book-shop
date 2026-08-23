package pl.pssoftware.bookstoreapi.catalog.dto;

import lombok.Getter;
import lombok.Setter;
import pl.pssoftware.bookstoreapi.catalog.entity.BookCategory;

import java.math.BigDecimal;

@Getter
@Setter
public class BookFilterDTO {
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private BookCategory category;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}
