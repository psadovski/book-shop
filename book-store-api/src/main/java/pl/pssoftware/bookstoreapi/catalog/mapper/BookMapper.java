package pl.pssoftware.bookstoreapi.catalog.mapper;

import org.mapstruct.Mapper;
import pl.pssoftware.bookstoreapi.catalog.dto.BookDTO;
import pl.pssoftware.bookstoreapi.catalog.entity.Book;
import pl.pssoftware.bookstoreapi.common.mapper.MapperConfiguration;

@Mapper(config = MapperConfiguration.class,
        uses = {AuthorMapper.class, PublisherMapper.class})
public interface BookMapper {

    BookDTO mapToDTO(Book book);
}
