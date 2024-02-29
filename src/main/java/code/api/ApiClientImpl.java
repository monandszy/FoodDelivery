package code.api;

import code.api.infrastructure.DefaultApi;
import code.api.model.InlineResponse200;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class ApiClientImpl implements ApiDAO {

   private WebClient webClient;
   private DefaultApi defaultApi;

   public void test() {
      Mono<InlineResponse200> inlineResponse200Mono = defaultApi.v1Get(ApiDAO.KEY, "78.30.83.227", "city");
      System.out.println(inlineResponse200Mono);
      webClient.get();
   }
}