package code.api.ipAddressApi;

import code.api.infrastructure.DefaultApi;
import code.api.model.InlineResponse200;
import code.component.manageRestaurant.manageDelivery.domain.Address;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class ApiClientImpl implements ApiDAO {

   private final AddressApiMapper addressApiMapper;
   private DefaultApi defaultApi;

   public Address getAddressFromApi(String ip) {
      Mono<InlineResponse200> inlineResponse200Mono = defaultApi.v1Get(ApiDAO.KEY, ip, ApiDAO.FIELDS);
      InlineResponse200 block = inlineResponse200Mono.block();
      return addressApiMapper.mapToAddress(block);
   }
}