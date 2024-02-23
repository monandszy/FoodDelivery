package code.component.manageOrder.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.time.OffsetDateTime;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

   private Integer id;
   private Order.OrderStatus status;
   private OffsetDateTime timeOfOrder;
}