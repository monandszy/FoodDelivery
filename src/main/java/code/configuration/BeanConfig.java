package code.configuration;

import code.api.ApiClient;
import code.api.infrastructure.DefaultApi;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BeanConfig {

   @Value("${api.abstract.url}")
   private String abstractUrl;

   @Bean
   public ObjectMapper objectMapper() {
      return new ObjectMapper()
          .registerModule(new JavaTimeModule())
          .registerModule(new Jdk8Module())
          .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
          .configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, false)
          .setSerializationInclusion(JsonInclude.Include.NON_NULL);
   }

   @Bean
   public ApiClient apiClient(WebClient webClient) {
      ApiClient apiClient = new ApiClient(webClient);
      apiClient.setBasePath(abstractUrl);
      return apiClient;
   }

   @Bean
   public DefaultApi petApi(final ApiClient apiClient) {
      return new DefaultApi(apiClient);
   }

}