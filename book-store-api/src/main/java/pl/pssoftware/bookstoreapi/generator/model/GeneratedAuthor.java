package pl.pssoftware.bookstoreapi.generator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class GeneratedAuthor {

    private Long id;

    private String name;

    private String surname;

    private LocalDate birthDate;

}
