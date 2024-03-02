package code.component.manageRestaurant.manageImages;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.core.io.ByteArrayResource;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImageMapper {

   @Mapping(source = "image", target = "image", qualifiedByName = "dtoMapping")
   ImageDTO mapToDTO(ImageEntity entity);

   @Mapping(source = "image", target = "image", qualifiedByName = "entityMapping")
   ImageEntity mapToEntity(ImageDTO dto);

   @Named("entityMapping")
   default byte[] entityMapping(ByteArrayResource file) {
      return file.getByteArray();
   }

   @Named("dtoMapping")
   default ByteArrayResource dtoMapping(byte[] bytes) {
      return new ByteArrayResource(bytes);
   }

   Set<ImageDTO> mapIToDTOList(Set<ImageEntity> images);
}