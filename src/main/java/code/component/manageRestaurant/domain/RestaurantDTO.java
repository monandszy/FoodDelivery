package code.component.manageRestaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.hibernate.validator.constraints.Range;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

   private Integer id;
   private String addressOutput;
   private String sellerOutput;
   @Range(min = 10, max = Long.MAX_VALUE)
   private Double deliveryRange;
}