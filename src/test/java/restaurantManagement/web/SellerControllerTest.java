package restaurantManagement.web;

import code.component.manageRestaurant.dao.MenuDAO;
import code.component.manageRestaurant.dao.MenuPositionDAO;
import code.component.manageRestaurant.dao.RestaurantDAO;
import code.component.manageRestaurant.domain.Menu;
import code.component.manageRestaurant.domain.MenuPosition;
import code.component.manageRestaurant.domain.Restaurant;
import code.component.manageRestaurant.web.sellerInput.MyMenuController;
import code.component.manageRestaurant.web.sellerInput.MyRestaurantController;
import code.component.manageRestaurant.web.sellerInput.MyRestaurantsController;
import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import restaurantManagement.EntityFixtures;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@AllArgsConstructor
public class SellerControllerTest {

   @Mock
   private RestaurantDAO restaurantDAO;
   @Mock
   private MenuPositionDAO menuPositionDAO;
   @Mock
   private MenuDAO menuDAO;

   @InjectMocks
   private MyRestaurantController restaurantController;
   @InjectMocks
   private MyRestaurantsController restaurantsController;
   @InjectMocks
   private MyMenuController menuController;

   @Test
   void thatRetrievingWorksCorrectly() {
      //given
      Integer id = 10;
      String sellerCode = "code";
      Integer page = 1;
      List<Restaurant> restaurants = List.of(EntityFixtures.someRestaurant(id, sellerCode));
      List<Menu> menus = List.of(EntityFixtures.someMenu(id));
      List<MenuPosition> menuPositions = List.of(EntityFixtures.someMenuPosition(id));
      ExtendedModelMap model = new ExtendedModelMap();

      Mockito.when(restaurantDAO.getPageByParent(id, page)).thenReturn(restaurants);
      Mockito.when(menuDAO.getPageByParent(id, page)).thenReturn(menus);
      Mockito.when(menuPositionDAO.getPageByParent(id, page)).thenReturn(menuPositions);
      //when
      String restaurantResult = restaurantsController.getRestaurants(sellerCode, page, model);
      String menuResult = restaurantController.getMenus(id, page, model);
      String menuPositionResult = menuController.getMenuPositions(id, page, model);
      //then
      Assertions.assertThat(restaurantResult).isEqualTo(MyRestaurantsController.MY_RESTAURANTS);
      Assertions.assertThat(menuResult).isEqualTo(MyRestaurantController.MY_RESTAURANT);
      Assertions.assertThat(menuPositionResult).isEqualTo(MyMenuController.MY_MENU);

      Assertions.assertThat(model.getAttribute("restaurantsPage")).isEqualTo(restaurantResult);
      Assertions.assertThat(model.getAttribute("restaurantPage")).isEqualTo(menus);
      Assertions.assertThat(model.getAttribute("menuPage")).isEqualTo(menuPositions);
   }
}