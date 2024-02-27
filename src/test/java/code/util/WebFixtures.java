package code.util;

import code.component.manageOrder.domain.OrderPositionDTO;
import code.component.manageRestaurant.domain.MenuDTO;
import code.component.manageRestaurant.domain.MenuPositionDTO;
import code.component.manageRestaurant.domain.RestaurantDTO;
import code.component.manageRestaurant.manageDelivery.domain.AddressDTO;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

import static code.component.manageRestaurant.domain.Menu.MenuType.MENU_TYPE2;

@UtilityClass
public class WebFixtures {

   public RestaurantDTO getRestaurantDTO() {
      return RestaurantDTO.builder().seller("").build();
   }


   public MenuDTO getMenuDTO() {
      return MenuDTO.builder().menuType(MENU_TYPE2).build();
   }

   public MenuPositionDTO getMenuPositionDTO() {
      return MenuPositionDTO.builder().price(BigDecimal.valueOf(1)).build();
   }

   public AddressDTO getAddressDTO () {
      return AddressDTO.builder().someStringField("").build();
   }

   public static OrderPositionDTO getOrderPosition() {
      return OrderPositionDTO.builder().build();
   }
}