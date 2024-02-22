package code;

import code.configuration.DatabaseContainerTestConfiguration;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

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

   private final TestRestTemplate restTemplate;

   @Test // test rest template probably has its own register page
   public void thatRegisterPageWorksCorrectly() {
      String url = String.format("http://localhost:%s/code/register", port);

      String renderedPage = this.restTemplate.getForObject(url, String.class);
      System.out.println(renderedPage);
      Assertions.assertThat(renderedPage).contains("Register");
   }
}