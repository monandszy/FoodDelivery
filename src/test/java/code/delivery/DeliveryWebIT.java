package code.delivery;

import code.api.ipAddressApi.ApiClientImpl;
import code.component.manageAccount.AccountService;
import code.component.manageRestaurant.domain.RestaurantDTO;
import code.component.manageRestaurant.domain.mapper.RestaurantDTOMapper;
import code.component.manageRestaurant.manageDelivery.AddressService;
import code.component.manageRestaurant.manageDelivery.domain.Address;
import code.component.manageRestaurant.manageDelivery.domain.AddressDTOMapper;
import code.component.manageRestaurant.web.clientOutput.DiscoverController;
import code.configuration.Constants;
import code.util.DataFixtures;
import code.util.WebFixtures;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static code.component.manageRestaurant.web.clientOutput.DiscoverController.DISCOVER;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = {
    DiscoverController.class,
})
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DeliveryWebIT {

   private MockMvc mockMvc;

   @MockBean
   private AddressService addressService;
   @MockBean
   private RestaurantDTOMapper dtoMapper;
   @MockBean
   private AddressDTOMapper addressDTOMapper;
   @MockBean
   private AccountService accountService;
   @MockBean
   private ApiClientImpl apiClient;

   @Test
   void testGetRestaurantsByIp() throws Exception {
      Integer pageNumber = 2;
      String ip = "ip";
      List<RestaurantDTO> restaurantPage = List.of(WebFixtures.getRestaurantDTO());
      Address address = DataFixtures.getAddress();
      Mockito.when(addressService.getAddress(ip)).thenReturn(address);
      Mockito.when(dtoMapper.mapRToDTOList(any())).thenReturn(restaurantPage);
      mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8087/" + DISCOVER)
              .param("ip", ip)
              .param("pageNumber", pageNumber.toString()))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.request().sessionAttribute(Constants.ADDRESS, address))
          .andExpect(MockMvcResultMatchers.model().attribute("pageNumber", pageNumber))
          .andExpect(MockMvcResultMatchers.model().attribute("restaurantsByAddressPage", restaurantPage))
          .andExpect(MockMvcResultMatchers.view().name("client/" + DISCOVER));
      Mockito.verify(addressService).getPageByAddress(address, pageNumber);
      Mockito.verify(addressService).getAddress(ip);
   }
}