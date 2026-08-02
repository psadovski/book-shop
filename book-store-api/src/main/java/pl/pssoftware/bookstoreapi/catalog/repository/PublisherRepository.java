package pl.pssoftware.bookstoreapi.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pssoftware.bookstoreapi.catalog.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
