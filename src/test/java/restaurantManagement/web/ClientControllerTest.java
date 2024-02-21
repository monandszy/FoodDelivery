package restaurantManagement.web;

import code.component.manageRestaurant.dao.MenuDAO;
import code.component.manageRestaurant.dao.MenuPositionDAO;
import code.component.manageRestaurant.domain.Menu;
import code.component.manageRestaurant.domain.MenuPosition;
import code.component.manageRestaurant.web.clientOutput.MenuController;
import code.component.manageRestaurant.web.clientOutput.RestaurantController;
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
public class ClientControllerTest {

   @Mock
   private MenuDAO menuDAO;

   @Mock
   private MenuPositionDAO menuPositionDAO;

   @InjectMocks
   private RestaurantController restaurantController;

   @InjectMocks
   private MenuController menuController;

   @Test
   void thatRetrievingWorksCorrectly() {
      //given
      Integer id = 10;
      Integer page = 1;
      List<Menu> menus = List.of(EntityFixtures.someMenu(id));
      List<MenuPosition> menuPositions = List.of(EntityFixtures.someMenuPosition(id));
      ExtendedModelMap model = new ExtendedModelMap();

      Mockito.when(menuDAO.getPageByParent(id, page)).thenReturn(menus);
      Mockito.when(menuPositionDAO.getPageByParent(id, page)).thenReturn(menuPositions);
      //when
      String menuResult = restaurantController.getMenus(id, page, model);
      String menuPositionResult = menuController.getMenuPositions(id, page, model);
      //then
      Assertions.assertThat(menuResult).isEqualTo(RestaurantController.RESTAURANT);
      Assertions.assertThat(menuPositionResult).isEqualTo(MenuController.MENU);

      Assertions.assertThat(model.getAttribute("restaurantPage")).isEqualTo(menus);
      Assertions.assertThat(model.getAttribute("menuPage")).isEqualTo(menuPositions);
   }

}