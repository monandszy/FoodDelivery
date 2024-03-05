package code.api.ipAddressApi;

import code.api.model.InlineResponse200;
import code.component.manageRestaurant.manageDelivery.domain.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressApiMapper {

   Address mapToAddress(InlineResponse200 inlineResponse200);

}