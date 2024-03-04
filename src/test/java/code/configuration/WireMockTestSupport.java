package code.configuration;

import code.component.api.ipAddressApi.ApiDAO;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import java.util.Map;

public interface WireMockTestSupport {

   default void stubForIp(final WireMockServer wireMockServer, String ip) {
      wireMockServer.stubFor(WireMock.get(
          WireMock.urlPathEqualTo("v1/?api_key=%s&ip_address=%s"
              .formatted(ApiDAO.KEY).formatted(ApiDAO.TEST_IP)))
              .willReturn(WireMock.aResponse()
                  .withHeader("Content-Type", "applicatioin/json")
                  .withBodyFile("wiremock/inlineResponse200_1.json")
                  .withTransformerParameters(Map.of("ip", ip))
                  .withTransformers("response-template")));
   }
}