package code.component.manageRestaurant.manageDelivery.domain;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressDTOMapper {
   public Address mapFromDTO(AddressDTO address);

   public AddressDTO mapToDTO(Address address);
}