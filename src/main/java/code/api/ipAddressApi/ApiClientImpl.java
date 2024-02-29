package code.api.ipAddressApi;

import code.api.infrastructure.DefaultApi;
import code.api.model.InlineResponse200;
import code.component.manageRestaurant.manageDelivery.AddressService;
import code.component.manageRestaurant.manageDelivery.domain.Address;
import code.component.manageRestaurant.manageDelivery.domain.AddressDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ApiClientImpl implements ApiDAO {

   private WebClient webClient;
   private DefaultApi defaultApi;
   private final AddressApiMapper addressApiMapper;
   private final AddressDTOMapper addressDTOMapper;
   private final AddressService addressService;

   public Address getAddressDTO(String ip) {
      Optional<Address> address = addressService.getAddressByIp(ip);
      if (address.isPresent()) {
         return address.get();
      } else {
         Mono<InlineResponse200> inlineResponse200Mono = defaultApi.v1Get(ApiDAO.KEY, ip, ApiDAO.FIELDS);
         InlineResponse200 block = inlineResponse200Mono.block();
         return addressApiMapper.mapToAddress(block);
      }
   }
}