package code.restaurantManagement.web;

import code.component.manageRestaurant.domain.Menu;
import code.component.manageRestaurant.domain.MenuPosition;
import code.component.manageRestaurant.domain.mapper.RestaurantDTOMapper;
import code.component.manageRestaurant.service.MenuPositionService;
import code.component.manageRestaurant.service.MenuService;
import code.component.manageRestaurant.service.RestaurantService;
import code.component.manageRestaurant.web.clientOutput.MenuController;
import code.component.manageRestaurant.web.clientOutput.RestaurantController;
import code.component.manageRestaurant.web.clientOutput.RestaurantsController;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ExtendedModelMap;

import java.util.List;

@WebMvcTest(controllers = {
    RestaurantController.class,
    RestaurantsController.class,
    MenuController.class
})
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientControllerIT {

   private MockMvc mockMvC;

   @MockBean
   private RestaurantService restaurantService;

   @MockBean
   private MenuPositionService menuPositionService;
   @MockBean
   private MenuService menuService;

   @MockBean
   private RestaurantDTOMapper restaurantDtoMapper;

   @Test
   void test() {
      //given
      Integer id = 10;
      Integer page = 1;
      List<Menu> menus = List.of(EntityFixtures.someMenu(id));
      List<MenuPosition> menuPositions = List.of(EntityFixtures.someMenuPosition(id));
      ExtendedModelMap model = new ExtendedModelMap();

      Mockito.when(menuService.getPageByRestaurantId(id, page)).thenReturn(menus);
      Mockito.when(menuPositionService.getPageByMenuId(id, page)).thenReturn(menuPositions);
      //when
//      String menuResult = restaurantController.getMenus(id, page, model);
//      String menuPositionResult = menuController.getMenuPositions(id, page, model);
//      then
//      Assertions.assertThat(menuResult).isEqualTo(RestaurantController.RESTAURANT);
//      Assertions.assertThat(menuPositionResult).isEqualTo(MenuController.MENU);
//
//      Assertions.assertThat(model.getAttribute("restaurantPage")).isEqualTo(menus);
//      Assertions.assertThat(model.getAttribute("menuPage")).isEqualTo(menuPositions);
   }
}