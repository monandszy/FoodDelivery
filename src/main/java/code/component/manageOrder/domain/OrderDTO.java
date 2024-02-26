package code.component.manageOrder.domain;

import code.component.manageRestaurant.manageDelivery.domain.AddressDTO;
import jakarta.validation.constraints.Pattern;
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

   @Pattern(regexp = "[0-9]*")
   private Integer id;
   private Order.OrderStatus status;
   private String timeOfOrder;
   private Integer restaurantId;
   private AddressDTO address;
}