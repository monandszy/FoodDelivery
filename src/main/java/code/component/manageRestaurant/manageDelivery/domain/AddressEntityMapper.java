package code.component.manageRestaurant.manageDelivery.domain;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public class AddressEntityMapper {

   public Address mapFromEntity(AddressEntity address) {
      return null;
   }
   public AddressEntity mapToEntity(Address address) {
      return null;
   }
}