package code.service.manageRestaurant.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.List;

@Value
@Builder
@With
public class Menu {

   List<MenuPosition> positions;

   MenuType menuType;

   enum MenuType {

   }
}