package code.component.manageRestaurant.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.math.BigDecimal;
import java.util.List;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuPositionDTO {

   @Pattern(regexp = "[0-9]*")
   private Integer id;
   @Pattern(regexp = "[0-9]*")
   private BigDecimal price;
   @NotEmpty
   private String name;
   private List<Integer> images;
}