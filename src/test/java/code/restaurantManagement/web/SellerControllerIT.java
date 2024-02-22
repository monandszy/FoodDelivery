package code.restaurantManagement.web;

import code.component.manageRestaurant.domain.Menu;
import code.component.manageRestaurant.domain.MenuPosition;
import code.component.manageRestaurant.domain.Restaurant;
import code.component.manageRestaurant.domain.mapper.RestaurantDTOMapper;
import code.component.manageRestaurant.service.MenuPositionService;
import code.component.manageRestaurant.service.MenuService;
import code.component.manageRestaurant.service.RestaurantService;
import code.component.manageRestaurant.web.sellerInput.MyMenuController;
import code.component.manageRestaurant.web.sellerInput.MyRestaurantController;
import code.component.manageRestaurant.web.sellerInput.MyRestaurantsController;
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
    MyRestaurantsController.class,
    MyRestaurantController.class,
    MyMenuController.class
})
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SellerControllerIT {

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
      String sellerCode = "code";
      Integer page = 1;
      Restaurant restaurant = EntityFixtures.someRestaurant(id, sellerCode);
      Menu menu = EntityFixtures.someMenu(id);
      MenuPosition menuPosition = EntityFixtures.someMenuPosition(id);
      List<Restaurant> restaurants = List.of(restaurant);
      List<Menu> menus = List.of(menu);
      List<MenuPosition> menuPositions = List.of(menuPosition);
      ExtendedModelMap model = new ExtendedModelMap();
//      DTOMapperImpl dtoMapper = new DTOMapperImpl();

      Mockito.when(restaurantService.getPageByParent(id, page)).thenReturn(restaurants);
      Mockito.when(menuService.getPageByParent(id, page)).thenReturn(menus);
      Mockito.when(menuPositionService.getPageByParent(id, page)).thenReturn(menuPositions);
      Mockito.when(restaurantDtoMapper.mapToDTO(menu)).thenReturn(restaurantDtoMapper.mapToDTO(menu));
      Mockito.when(restaurantDtoMapper.mapToDTO(menuPosition)).thenReturn(restaurantDtoMapper.mapToDTO(menuPosition));
      Mockito.when(restaurantDtoMapper.mapToDTO(restaurant)).thenReturn(restaurantDtoMapper.mapToDTO(restaurant));
      //when
//      String restaurantResult = restaurantsController.getRestaurants(sellerCode, page, model);
//      String menuResult = restaurantController.getMenus(id, page, model);
//      String menuPositionResult = menuController.getMenuPositions(id, page, model);
      //then
//      Assertions.assertThat(restaurantResult).isEqualTo(MyRestaurantsController.MY_RESTAURANTS);
//      Assertions.assertThat(menuResult).isEqualTo(MyRestaurantController.MY_RESTAURANT);
//      Assertions.assertThat(menuPositionResult).isEqualTo(MyMenuController.MY_MENU);

//      Assertions.assertThat(model.getAttribute("restaurantsPage")).isEqualTo(restaurantResult);
//      Assertions.assertThat(model.getAttribute("restaurantPage")).isEqualTo(menus);
//      Assertions.assertThat(model.getAttribute("menuPage")).isEqualTo(menuPositions);
   }

}