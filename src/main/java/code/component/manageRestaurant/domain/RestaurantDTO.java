package code.component.manageRestaurant.domain;

import code.component.manageRestaurant.manageDelivery.domain.AddressDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

   @Pattern(regexp = "[0-9]*")
   private Integer id;
   @NotNull
   private AddressDTO addressInput;
   private String addressOutput;
   private String sellerOutput;

   @Pattern(regexp = "[0-9]*")
   private BigInteger deliveryRange;
}