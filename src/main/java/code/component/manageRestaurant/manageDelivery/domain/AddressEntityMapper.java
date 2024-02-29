package code.component.manageRestaurant.manageDelivery.domain;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressEntityMapper {

   public Address mapFromEntity(AddressEntity address);
   public AddressEntity mapToEntity(Address address);
}