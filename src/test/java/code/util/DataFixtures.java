package code.util;

import code.component.manageOrder.domain.Order;
import code.component.manageOrder.domain.OrderPosition;
import code.component.manageRestaurant.domain.Menu;
import code.component.manageRestaurant.domain.MenuPosition;
import code.component.manageRestaurant.domain.Restaurant;
import code.component.manageRestaurant.manageDelivery.domain.Address;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class DataFixtures {
   public static Restaurant getRestaurant() {
      return Restaurant.builder()
          .deliveryRange(1D)
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
          .latitude(1D)
          .longitude(1D)
          .postalCode("code")
          .build();
   }

   public static OrderPosition getOrderPosition() {
      return OrderPosition.builder().build();
   }

   public static Restaurant getRestLonLat2() {
      return getRestaurant().withAddress(getAddress()
          .withLatitude(2D).withLongitude(2D)).withDeliveryRange(1000D);
   }

   public static Restaurant getRestLonLat3() {
      return getRestaurant().withAddress(getAddress()
          .withLatitude(3D).withLongitude(3D)).withDeliveryRange(1000D);
   }

   public static Restaurant getRestLonLat1() {
      return getRestaurant().withAddress(getAddressLonLat1()).withDeliveryRange(0D);
   }

   public static Address getAddressLonLat1() {
      return getAddress().withLatitude(1D).withLongitude(1D);
   }
}