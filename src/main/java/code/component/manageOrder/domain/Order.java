package code.component.manageOrder.domain;

import code.component.manageAccount.domain.Account;
import code.component.manageRestaurant.manageDelivery.Address;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.With;

import java.time.OffsetDateTime;
import java.util.List;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
public class Order {

   Integer id;
   OrderStatus status;
   OffsetDateTime timeOfOrder;
   List<OrderPosition> menuPositions;
   Account client;
   Address address;
   Integer restaurantId;

   public enum OrderStatus {
      IN_PROGRESS,
      TRAVELLING,
      COMPLETED
   }

}