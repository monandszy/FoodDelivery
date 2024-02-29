package code.util;

import code.component.manageOrder.domain.Order;
import code.component.manageOrder.domain.OrderPosition;
import code.component.manageRestaurant.domain.Menu;
import code.component.manageRestaurant.domain.MenuPosition;
import code.component.manageRestaurant.domain.Restaurant;
import code.component.manageRestaurant.manageDelivery.domain.Address;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.OffsetDateTime;

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

   public static Order getOrder() {
      return Order.builder()
          .status(Order.OrderStatus.IN_PROGRESS)
          .timeOfOrder(OffsetDateTime.now())
          .build();
   }

   public static Address getAddress() {
      return Address.builder()
          .ipAddress("ip")
          .city("city")
          .latitude(BigDecimal.valueOf(1))
          .longitude(BigDecimal.valueOf(2))
          .postalCode("code")
          .build();
   }

   public static OrderPosition getOrderPosition() {
      return OrderPosition.builder().build();
   }
}