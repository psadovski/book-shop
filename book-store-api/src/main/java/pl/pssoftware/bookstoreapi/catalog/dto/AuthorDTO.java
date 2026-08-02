package pl.pssoftware.bookstoreapi.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {

    private Long id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
}
