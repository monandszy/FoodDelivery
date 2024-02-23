package code.component.manageOrder.domain;

import code.component.manageRestaurant.manageDelivery.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

   private Integer id;
   private Order.OrderStatus status;
   private String timeOfOrder;
   private Integer restaurantId;
   private AddressDTO address;
}