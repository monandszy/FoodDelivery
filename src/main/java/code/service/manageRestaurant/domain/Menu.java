package code.service.manageRestaurant.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.With;

import java.util.List;

@With
@Value
@Builder
@EqualsAndHashCode(of = {})
@ToString(of = {})
public class Menu {

   Integer id;
   MenuType menuType;
   List<MenuPosition> positions;

   enum MenuType {
      MENU_TYPE
   }
}