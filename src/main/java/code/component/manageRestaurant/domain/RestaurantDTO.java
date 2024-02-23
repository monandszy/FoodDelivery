package code.component.manageRestaurant.domain;

import code.component.manageRestaurant.manageDelivery.domain.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.math.BigInteger;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

   private Integer id;
   private AddressDTO address;
   private String seller;
   private BigInteger deliveryRange;
}