package code.api;

import code.api.infrastructure.DefaultApi;
import code.api.ipAddressApi.ApiDAO;
import code.api.model.InlineResponse200;
import code.configuration.AbstractSpringBootIT;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class IpAddressApiIT extends AbstractSpringBootIT {

   private DefaultApi defaultApi;

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