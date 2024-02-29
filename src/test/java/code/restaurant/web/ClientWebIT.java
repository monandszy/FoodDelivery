package code.restaurant.web;

import code.component.manageAccount.AccountService;
import code.component.manageRestaurant.domain.MenuDTO;
import code.component.manageRestaurant.domain.MenuPositionDTO;
import code.component.manageRestaurant.domain.mapper.RestaurantDTOMapper;
import code.component.manageRestaurant.manageDelivery.AddressService;
import code.component.manageRestaurant.manageDelivery.domain.AddressDTOMapper;
import code.component.manageRestaurant.service.MenuPositionService;
import code.component.manageRestaurant.service.MenuService;
import code.component.manageRestaurant.service.RestaurantService;
import code.component.manageRestaurant.web.clientOutput.DiscoverController;
import code.component.manageRestaurant.web.clientOutput.MenuController;
import code.component.manageRestaurant.web.clientOutput.RestaurantController;
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

import static code.component.manageRestaurant.web.clientOutput.MenuController.MENU;
import static code.component.manageRestaurant.web.clientOutput.MenuController.MENU_GET;
import static code.component.manageRestaurant.web.clientOutput.RestaurantController.RESTAURANT;
import static code.component.manageRestaurant.web.clientOutput.RestaurantController.RESTAURANT_GET;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = {
    DiscoverController.class,
    RestaurantController.class,
    MenuController.class,
})
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientWebIT {

   private MockMvc mockMvc;

   @MockBean
   private AddressService addressService;
   @MockBean
   private MenuService menuService;
   @MockBean
   private MenuPositionService menuPositionService;
   @MockBean
   private AccountService accountService;
   @MockBean
   private RestaurantDTOMapper dtoMapper;
   @MockBean
   private AddressDTOMapper addressDTOMapper;
   @MockBean
   private RestaurantService restaurantService;

   @Test
   void testGetRestaurant() throws Exception {
      Integer restaurantId = 1;
      Integer pageNumber = 2;
      List<MenuDTO> restaurantPage = List.of(WebFixtures.getMenuDTO());
      Mockito.when(dtoMapper.mapMToDTOList(any())).thenReturn(restaurantPage);
      mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8087/" +
                  RESTAURANT_GET.replace("{restaurantId}", restaurantId.toString()))
              .queryParam("pageNumber", pageNumber.toString()))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.request().sessionAttribute("RESTAURANT", restaurantId))
          .andExpect(MockMvcResultMatchers.model().attribute("restaurantId", restaurantId))
          .andExpect(MockMvcResultMatchers.model().attribute("pageNumber", pageNumber))
          .andExpect(MockMvcResultMatchers.model().attribute("restaurantPage", restaurantPage))
          .andExpect(MockMvcResultMatchers.view().name("client/" + RESTAURANT));
      Mockito.verify(menuService).getPageByRestaurant(restaurantId, pageNumber);
   }

   @Test
   void testGetMenu() throws Exception {
      Integer menuId = 1;
      int restaurantId = 1;
      List<MenuPositionDTO> menuPositions = List.of(WebFixtures.getMenuPositionDTO());
      Mockito.when(dtoMapper.mapMPToDTOList(any())).thenReturn(menuPositions);
      mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8087/" +
                  MENU_GET.replace("{menuId}", menuId.toString()))
              .queryParam("restaurantId", Integer.toString(restaurantId)))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.model().attribute("menuPositions", menuPositions))
          .andExpect(MockMvcResultMatchers.view().name("client/" + MENU));
      Mockito.verify(menuPositionService).getAllMenuPositions(menuId);
   }
}