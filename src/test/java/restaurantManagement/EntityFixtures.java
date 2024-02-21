package restaurantManagement;

import code.component.manageRestaurant.domain.Menu;
import code.component.manageRestaurant.domain.MenuPosition;
import code.component.manageRestaurant.domain.Restaurant;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.List;

@UtilityClass
public class EntityFixtures {


   public static MenuPosition someMenuPosition(Integer id) {
      return MenuPosition.builder()
          .id(id)
          .price(BigDecimal.valueOf(1))
          .build();
   }

   public static Menu someMenu(Integer id) {
      return Menu.builder()
          .id(id)
          .menuType(Menu.MenuType.MENU_TYPE)
          .positions(List.of(someMenuPosition(1)))
          .build();
   }

   public static Restaurant someRestaurant(Integer id, String sellerCode) {
      return Restaurant.builder()
          .id(id)
          .menus(List.of(someMenu(1)))
          .sellerAccountCode(sellerCode)
          .build();
   }
}