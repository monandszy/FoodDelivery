package code.restaurant.web;

import code.component.manageAccount.AccountService;
import code.component.manageRestaurant.domain.RestaurantDTO;
import code.component.manageRestaurant.domain.mapper.RestaurantDTOMapper;
import code.component.manageRestaurant.manageDelivery.AddressService;
import code.component.manageRestaurant.manageDelivery.domain.AddressDTOMapper;
import code.component.manageRestaurant.service.RestaurantService;
import code.component.manageRestaurant.web.sellerInput.SellerRestaurantsController;
import code.configuration.Constants;
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

import static code.component.manageRestaurant.web.sellerInput.SellerRestaurantsController.MY_RESTAURANTS;
import static code.component.manageRestaurant.web.sellerInput.SellerRestaurantsController.MY_RESTAURANTS_ADD;
import static code.component.manageRestaurant.web.sellerInput.SellerRestaurantsController.MY_RESTAURANTS_DELETE;
import static code.component.manageRestaurant.web.sellerInput.SellerRestaurantsController.MY_RESTAURANTS_GET;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = SellerRestaurantsController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@AutoConfigureMockMvc(addFilters = false)
public class SellerRestaurantsWebIT {

   private MockMvc mockMvc;

   @MockBean
   private RestaurantService restaurantService;
   @MockBean
   private AccountService accountService;
   @MockBean
   private RestaurantDTOMapper dtoMapper;
   @MockBean
   private AddressDTOMapper addressDTOMapper;
   @MockBean
   private AddressService addressService;

   @Test
   void testGet() throws Exception {
      String userName = "seller";
      Integer pageNumber = 2;
      List<RestaurantDTO> restaurantsPage = List.of(WebFixtures.getRestaurantDTO());
      Mockito.when(accountService.getAuthenticatedUserName()).thenReturn(userName);
      Mockito.when(dtoMapper.mapRToDTOList(any())).thenReturn(restaurantsPage);
      mockMvc.perform(MockMvcRequestBuilders.get(Constants.URL + MY_RESTAURANTS_GET)
              .param("pageNumber", pageNumber.toString()))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.model().attribute("pageNumber", pageNumber))
          .andExpect(MockMvcResultMatchers.model().attribute("restaurantsPage", restaurantsPage))
          .andExpect(MockMvcResultMatchers.view().name("seller/" + MY_RESTAURANTS));
      Mockito.verify(restaurantService).getPageBySellerId(userName, pageNumber);
   }

   @Test
   void testAdd() throws Exception {
      String ip = "ip";
      String userName = "seller";
      RestaurantDTO restaurantDTO = WebFixtures.getRestaurantDTO();
      Mockito.when(accountService.getAuthenticatedUserName()).thenReturn(userName);
      Mockito.when(accountService.getCurrentIp()).thenReturn(ip);
      mockMvc.perform(MockMvcRequestBuilders.post(Constants.URL + MY_RESTAURANTS_ADD)
              .flashAttr("restaurantDTO", restaurantDTO))
          .andExpect(MockMvcResultMatchers.redirectedUrl("/" + MY_RESTAURANTS_GET))
          .andExpect(MockMvcResultMatchers.status().isFound());
      Mockito.verify(restaurantService).add(null, null, userName);
      Mockito.verify(addressService).getAddress(ip);
   }

   @Test
   void testDelete() throws Exception {
      Integer restaurantId = 1;
      mockMvc.perform(MockMvcRequestBuilders.post(Constants.URL +
              MY_RESTAURANTS_DELETE.replace("{restaurantId}", restaurantId.toString())))
          .andExpect(MockMvcResultMatchers.redirectedUrl("/" + MY_RESTAURANTS_GET))
          .andExpect(MockMvcResultMatchers.status().isFound());
      Mockito.verify(restaurantService).deleteById(restaurantId);
   }

}