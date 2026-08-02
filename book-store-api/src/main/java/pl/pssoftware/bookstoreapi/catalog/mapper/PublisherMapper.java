package pl.pssoftware.bookstoreapi.catalog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.pssoftware.bookstoreapi.catalog.dto.PublisherDTO;
import pl.pssoftware.bookstoreapi.catalog.entity.Publisher;
import pl.pssoftware.bookstoreapi.common.mapper.MapperConfiguration;

@Mapper(config = MapperConfiguration.class)
public interface PublisherMapper {

    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);

    PublisherDTO mapToDTO(Publisher publisher);
}
