package code.component.manageRestaurant.manageDelivery.domain;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressEntityMapper {

   public Address mapFromEntity(AddressEntity address);
   public AddressEntity mapToEntity(Address address);

   List<Address> mapAFromEntityList(List<AddressEntity> all);
   List<AddressEntity> mapAToEntityList(List<Address> all);
}