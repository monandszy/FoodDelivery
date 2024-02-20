package code.component.manageRestaurant.domain;

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

   private Integer id;
   private Menu.MenuType menuType;

   enum MenuType {
      MENU_TYPE
   }
}