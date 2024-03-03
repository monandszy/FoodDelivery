package code.restaurant.web;

import code.component.manageRestaurant.domain.MenuPositionDTO;
import code.component.manageRestaurant.domain.mapper.RestaurantDTOMapper;
import code.component.manageRestaurant.service.MenuPositionService;
import code.component.manageRestaurant.web.sellerInput.SellerMenuController;
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

import static code.component.manageRestaurant.web.sellerInput.SellerMenuController.MY_MENU;
import static code.component.manageRestaurant.web.sellerInput.SellerMenuController.MY_MENU_ADD;
import static code.component.manageRestaurant.web.sellerInput.SellerMenuController.MY_MENU_DELETE;
import static code.component.manageRestaurant.web.sellerInput.SellerMenuController.MY_MENU_GET;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = SellerMenuController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@AutoConfigureMockMvc(addFilters = false)
public class SellerMenuWebIT {

   private MockMvc mockMvc;

   @MockBean
   private MenuPositionService menuPositionService;

   @MockBean
   private RestaurantDTOMapper dtoMapper;

   @Test
   void testGet() throws Exception {
      Integer menuId = 1;
      Integer expectedPageNumber = 0;
      List<MenuPositionDTO> menuPage = List.of(WebFixtures.getMenuPositionDTO());
      Mockito.when(dtoMapper.mapMPToDTOList(any())).thenReturn(menuPage);
      mockMvc.perform(MockMvcRequestBuilders.get(Constants.URL +
                  MY_MENU_GET.replace("{menuId}", menuId.toString())))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.model().attribute("pageNumber", expectedPageNumber))
          .andExpect(MockMvcResultMatchers.model().attribute("menuPage", menuPage))
          .andExpect(MockMvcResultMatchers.view().name("seller/" + MY_MENU));
      Mockito.verify(menuPositionService).getPageByMenu(menuId, expectedPageNumber);
   }

   @Test
   void testAdd() throws Exception {
      Integer menuId = 1;
      MenuPositionDTO menuPositionDTO = WebFixtures.getMenuPositionDTO();
      mockMvc.perform(MockMvcRequestBuilders.post(Constants.URL + MY_MENU_ADD)
              .param("menuId", menuId.toString())
              .flashAttr("menuPositionDTO", menuPositionDTO))
          .andExpect(MockMvcResultMatchers.redirectedUrl("/"
              + MY_MENU_GET.replace("{menuId}", menuId.toString())))
          .andExpect(MockMvcResultMatchers.status().isFound());
      Mockito.verify(menuPositionService).add(null, null, menuId);
   }

   @Test
   void testDelete() throws Exception {
      int menuId = 1;
      Integer menuPositionId = 1;
      mockMvc.perform(MockMvcRequestBuilders.post(Constants.URL +
                  MY_MENU_DELETE.replace("{menuPositionId}", menuPositionId.toString()))
              .flashAttr("menuId", Integer.toString(menuId)))
          .andExpect(MockMvcResultMatchers.redirectedUrl("/"
              + MY_MENU_GET.replace("{menuId}", Integer.toString(menuId))))
          .andExpect(MockMvcResultMatchers.status().isFound());
      Mockito.verify(menuPositionService).deleteById(menuPositionId);
   }
}