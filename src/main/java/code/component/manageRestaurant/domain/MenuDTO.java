package code.component.manageRestaurant.domain;

import jakarta.validation.constraints.NotEmpty;
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
public class MenuDTO {

   @Pattern(regexp = "[0-9]*")
   private Integer id;
   @NotEmpty
   private Menu.MenuType menuType;

}