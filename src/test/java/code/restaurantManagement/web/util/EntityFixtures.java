package code.restaurantManagement.web.util;

import code.component.manageRestaurant.domain.MenuDTO;
import code.component.manageRestaurant.domain.MenuPositionDTO;
import code.component.manageRestaurant.domain.RestaurantDTO;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

import static code.component.manageRestaurant.domain.Menu.MenuType.MENU_TYPE2;

@UtilityClass
public class EntityFixtures {

   public RestaurantDTO getRestaurantDTO() {
      return RestaurantDTO.builder().seller("").build();
   }


   public MenuDTO getMenuDTO() {
      return MenuDTO.builder().menuType(MENU_TYPE2).build();
   }

   public static MenuPositionDTO getMenuPositionDTO() {
      return MenuPositionDTO.builder().price(BigDecimal.valueOf(1)).build();
   }
}