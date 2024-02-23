package code.component.manageOrder.domain;

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

   public enum OrderStatus {
      IN_PROGRESS,
      TRAVELLING,
      COMPLETED
   }

}