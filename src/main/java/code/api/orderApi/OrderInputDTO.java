package code.api.orderApi;

import code.component.manageRestaurant.manageDelivery.domain.AddressDTO;
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
public class OrderInputDTO {
   private AddressDTO addressDTO;
   private Integer restaurantId;
   private Integer[] selected;
}