package code.restaurantManagement.web;

import code.component.manageAccount.AccountService;
import code.component.manageRestaurant.domain.MenuDTO;
import code.component.manageRestaurant.domain.mapper.RestaurantDTOMapper;
import code.component.manageRestaurant.service.MenuService;
import code.component.manageRestaurant.web.sellerInput.SellerRestaurantController;
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

import static code.component.manageRestaurant.web.sellerInput.SellerRestaurantController.MY_RESTAURANT;
import static code.component.manageRestaurant.web.sellerInput.SellerRestaurantController.MY_RESTAURANT_ADD;
import static code.component.manageRestaurant.web.sellerInput.SellerRestaurantController.MY_RESTAURANT_DELETE;
import static code.component.manageRestaurant.web.sellerInput.SellerRestaurantController.MY_RESTAURANT_GET;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = SellerRestaurantController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@AutoConfigureMockMvc(addFilters = false)
public class SellerRestaurantWebIT {

   private MockMvc mockMvc;

   @MockBean
   private MenuService menuService;

   @MockBean
   private AccountService accountService;

   @MockBean
   private RestaurantDTOMapper dtoMapper;

   @Test
   void testGet() throws Exception {
      Integer restaurantId = 1;
      Integer pageNumber = 2;
      List<MenuDTO> restaurantPage = List.of(WebFixtures.getMenuDTO());
      Mockito.when(dtoMapper.mapMToDTOList(any())).thenReturn(restaurantPage);
      mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8087/" +
                  MY_RESTAURANT_GET.replace("{restaurantId}", restaurantId.toString()))
              .queryParam("pageNumber", pageNumber.toString()))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.model().attribute("restaurantId", restaurantId))
          .andExpect(MockMvcResultMatchers.model().attribute("pageNumber", pageNumber))
          .andExpect(MockMvcResultMatchers.model().attribute("restaurantPage", restaurantPage))
          .andExpect(MockMvcResultMatchers.view().name("seller/" + MY_RESTAURANT));
      Mockito.verify(menuService).getPageByRestaurant(restaurantId, pageNumber);
   }

   @Test
   void testAdd() throws Exception {
      Integer restaurantId = 1;
      MenuDTO menuDTO = WebFixtures.getMenuDTO();
      mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8087/" + MY_RESTAURANT_ADD)
              .param("restaurantId", restaurantId.toString())
              .flashAttr("menuDTO", menuDTO))
          .andExpect(MockMvcResultMatchers.view().name("redirect:/"
              + MY_RESTAURANT_GET.replace("{restaurantId}", restaurantId.toString())))
          .andExpect(MockMvcResultMatchers.status().isFound());
      Mockito.verify(menuService).add(null, restaurantId);
   }

   @Test
   void testDelete() throws Exception {
      int restaurantId = 1;
      Integer menuId = 1;
      mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8087/" +
                  MY_RESTAURANT_DELETE.replace("{menuId}", menuId.toString()))
              .param("restaurantId", Integer.toString(restaurantId)))
          .andExpect(MockMvcResultMatchers.view().name("redirect:/"
              + MY_RESTAURANT_GET.replace("{restaurantId}", Integer.toString(restaurantId))))
          .andExpect(MockMvcResultMatchers.status().isFound());
      Mockito.verify(menuService).deleteById(menuId);
   }
}