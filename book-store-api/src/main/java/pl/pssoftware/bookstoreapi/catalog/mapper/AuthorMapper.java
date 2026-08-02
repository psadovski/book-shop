package pl.pssoftware.bookstoreapi.catalog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.pssoftware.bookstoreapi.catalog.dto.AuthorDTO;
import pl.pssoftware.bookstoreapi.catalog.entity.Author;
import pl.pssoftware.bookstoreapi.common.mapper.MapperConfiguration;

@Mapper(config = MapperConfiguration.class)
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDTO mapToDTO(Author author);
}
