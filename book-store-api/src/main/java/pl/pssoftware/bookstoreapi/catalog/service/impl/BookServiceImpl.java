package pl.pssoftware.bookstoreapi.catalog.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pssoftware.bookstoreapi.catalog.dto.BookDTO;
import pl.pssoftware.bookstoreapi.catalog.mapper.BookMapper;
import pl.pssoftware.bookstoreapi.catalog.repository.BookRepository;
import pl.pssoftware.bookstoreapi.catalog.service.BookService;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<BookDTO> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).map(bookMapper::mapToDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public BookDTO findById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::mapToDTO)
                .orElseThrow(EntityNotFoundException::new);
    }
}
