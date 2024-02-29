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

@Component
@AllArgsConstructor
public class ApiClientImpl implements ApiDAO {

   private final AddressApiMapper addressApiMapper;
   private final AddressDTOMapper addressDTOMapper;
   private final AddressService addressService;
   private WebClient webClient;
   private DefaultApi defaultApi;

   public Address getAddressFromApi(String ip) {
      Mono<InlineResponse200> inlineResponse200Mono = defaultApi.v1Get(ApiDAO.KEY, ip, ApiDAO.FIELDS);
      InlineResponse200 block = inlineResponse200Mono.block();
      return addressApiMapper.mapToAddress(block);
   }
}