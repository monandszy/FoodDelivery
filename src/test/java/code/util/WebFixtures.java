package code.util;

import code.component.manageAccount.domain.AccountDTO;
import code.component.manageOrder.domain.Order;
import code.component.manageOrder.domain.OrderDTO;
import code.component.manageOrder.domain.OrderPositionDTO;
import code.component.manageRestaurant.domain.MenuDTO;
import code.component.manageRestaurant.domain.MenuPositionDTO;
import code.component.manageRestaurant.domain.RestaurantDTO;
import code.component.manageRestaurant.manageDelivery.domain.AddressDTO;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static code.component.manageRestaurant.domain.Menu.MenuType.MENU_TYPE2;

@UtilityClass
public class WebFixtures {

   public RestaurantDTO getRestaurantDTO() {
      return RestaurantDTO.builder().deliveryRange(1D).build();
   }

   public MenuDTO getMenuDTO() {
      return MenuDTO.builder().menuType(MENU_TYPE2).build();
   }

   public MenuPositionDTO getMenuPositionDTO() {
      return MenuPositionDTO.builder()
          .name("test")
          .price(BigDecimal.valueOf(1))
          .build();
   }

   public static OrderPositionDTO getOrderPosition() {
      return OrderPositionDTO.builder().build();
   }

   public static OrderDTO getOrder() {
      return OrderDTO.builder()
          .status(Order.OrderStatus.IN_PROGRESS)
          .timeOfOrder(OffsetDateTime.now().toString())
          .restaurantId(1)
          .build();
   }

   public static AccountDTO getAccount() {
      return AccountDTO.builder()
          .userName("anonymousUser")
          .password("anonymousUser")
          .build();
   }

   public static AddressDTO getAddress() {
      return AddressDTO.builder()
          .ipAddress("someIp")
          .city("city")
          .latitude(1D)
          .longitude(1D)
          .postalCode("code")
          .build();
   }
}