package pl.pssoftware.bookstoreapi.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pssoftware.bookstoreapi.catalog.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
