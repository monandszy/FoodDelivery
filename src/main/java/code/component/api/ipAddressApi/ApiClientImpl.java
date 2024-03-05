package code.component.api.ipAddressApi;

import code.component.manageRestaurant.manageDelivery.domain.Address;
import code.openApi.infrastructure.DefaultApi;
import code.openApi.model.InlineResponse200;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class ApiClientImpl implements ApiDAO {

   private AddressApiMapper addressApiMapper;
   private DefaultApi defaultApi;

   public Address getAddressFromApi(String ip) {
      Mono<InlineResponse200> inlineResponse200Mono = defaultApi.v1Get(ApiDAO.KEY, ip, ApiDAO.FIELDS);
      InlineResponse200 block = inlineResponse200Mono.block();
      return addressApiMapper.mapToAddress(block);
   }
}