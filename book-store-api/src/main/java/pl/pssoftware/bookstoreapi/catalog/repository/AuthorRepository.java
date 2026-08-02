package pl.pssoftware.bookstoreapi.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pssoftware.bookstoreapi.catalog.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
