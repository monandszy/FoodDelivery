package code.component.manageRestaurant.manageDelivery.domain;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public class AddressDTOMapper {
   public Address mapFromDTO(AddressDTO address) {
      return null;
   }

   public Address mapToDTO(AddressDTO address) {
      return null;
   }
}