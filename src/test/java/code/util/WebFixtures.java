package code.util;

import code.component.manageOrder.domain.OrderPositionDTO;
import code.component.manageRestaurant.domain.MenuDTO;
import code.component.manageRestaurant.domain.MenuPositionDTO;
import code.component.manageRestaurant.domain.RestaurantDTO;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.BigInteger;

import static code.component.manageRestaurant.domain.Menu.MenuType.MENU_TYPE2;

@UtilityClass
public class WebFixtures {

   public RestaurantDTO getRestaurantDTO() {
      return RestaurantDTO.builder().deliveryRange(BigInteger.valueOf(1)).build();
   }

   public MenuDTO getMenuDTO() {
      return MenuDTO.builder().menuType(MENU_TYPE2).build();
   }

   public MenuPositionDTO getMenuPositionDTO() {
      return MenuPositionDTO.builder().price(BigDecimal.valueOf(1)).build();
   }

   public static OrderPositionDTO getOrderPosition() {
      return OrderPositionDTO.builder().build();
   }
}