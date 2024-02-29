package code.api.ipAddressApi;

import code.api.model.InlineResponse200;
import code.component.manageRestaurant.manageDelivery.domain.AddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressApiMapper {

   AddressDTO mapToAddress(InlineResponse200 inlineResponse200);

}