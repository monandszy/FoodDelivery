package code;

import code.api.ApiDAO;
import code.api.infrastructure.DefaultApi;
import code.api.model.InlineResponse200;
import code.configuration.DatabaseContainerTestConfiguration;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@ActiveProfiles("test")
@Import(DatabaseContainerTestConfiguration.class)
@SpringBootTest(
    classes = {ApplicationRunner.class},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc(addFilters = false)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SpringBootApplicationTest {

   @LocalServerPort
   private int port;

   private final WebClient webClient;
   private final DefaultApi defaultApi;

   @Test
   public void thatApplicationWorksCorrectly() {
      Mono<InlineResponse200> inlineResponse200Mono = defaultApi.v1Get(ApiDAO.KEY, "78.30.83.227", "city");
      webClient.get();
   }
}