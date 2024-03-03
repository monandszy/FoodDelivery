package code.component.manageOrder.domain;

import code.component.manageRestaurant.domain.MenuPositionDTO;
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
public class OrderPositionDTO {

   @Pattern(regexp = "[0-9]*")
   private Integer id;
   private MenuPositionDTO menuPositionDTO;
}