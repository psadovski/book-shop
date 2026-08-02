package pl.pssoftware.bookstoreapi.catalog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.pssoftware.bookstoreapi.catalog.dto.BookDTO;

public interface BookService {

    Page<BookDTO> findAll(Pageable pageable);

    BookDTO findById(Long id);
}
