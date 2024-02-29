package code;

import code.api.infrastructure.DefaultApi;
import code.api.ipAddressApi.ApiDAO;
import code.api.model.InlineResponse200;
import code.configuration.DatabaseContainerTestConfiguration;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@ActiveProfiles("test")
@Import(DatabaseContainerTestConfiguration.class)
@SpringBootTest(
    classes = {ApplicationRunner.class},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc(addFilters = false)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SpringBootApplicationTest {

   private final DefaultApi defaultApi;
   @LocalServerPort
   private int port;
   private final HttpServletRequest request;


   @Test
   public void testAbstractApiConnection() {
      Mono<InlineResponse200> inlineResponse200Mono = defaultApi.v1Get(
          ApiDAO.KEY, ApiDAO.TEST_IP, ApiDAO.FIELDS);
      InlineResponse200 block = inlineResponse200Mono.block();
      Assertions.assertEquals(ApiDAO.TEST_IP, block.getIpAddress());
      Assertions.assertEquals("Gadki", block.getCity());
      Assertions.assertEquals("62-023", block.getPostalCode());
      Assertions.assertEquals(BigDecimal.valueOf(17.0398), block.getLongitude());
      Assertions.assertEquals(BigDecimal.valueOf(52.3094), block.getLatitude());
   }
}