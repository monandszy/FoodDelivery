package code.component.api.orderApi;

import code.component.manageRestaurant.manageDelivery.domain.AddressDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.lang.NonNull;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderInputDTO {

   private AddressDTO addressDTO;
   @NonNull
   private Integer restaurantId;
   @NotEmpty
   private Integer[] selected;
}