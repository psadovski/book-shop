package pl.pssoftware.bookstoreapi.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private PublisherDTO publisher;
    private Set<AuthorDTO> authors;
}
