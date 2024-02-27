package code.util;

import code.component.manageRestaurant.domain.Menu;
import code.component.manageRestaurant.domain.MenuPosition;
import code.component.manageRestaurant.domain.Restaurant;

import java.math.BigDecimal;
import java.math.BigInteger;

public class DataFixtures {
   public static Restaurant getRestaurant() {
      return Restaurant.builder()
          .deliveryRange(BigInteger.valueOf(1))
          .build();
   }

   public static Menu getMenu() {
      return Menu.builder()
          .menuType(Menu.MenuType.MENU_TYPE1)
          .build();
   }

   public static MenuPosition getMenuPosition() {
      return MenuPosition.builder()
          .price(BigDecimal.valueOf(1))
          .build();
   }
}