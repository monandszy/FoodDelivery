package code.component.manageRestaurant.web.clientOutput;

import code.component.manageRestaurant.domain.MenuPosition;
import code.component.manageRestaurant.domain.MenuPositionDTO;
import code.component.manageRestaurant.domain.mapper.RestaurantDTOMapper;
import code.component.manageRestaurant.service.MenuPositionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class MenuController {

   public static final String MENU = "menu";

   private MenuPositionService menuPositionService;
   private RestaurantDTOMapper restaurantDtoMapper;

   @GetMapping(MENU + "/get/{menuId}")
   public String getMenuPositions(
       @PathVariable(value = "menuId") Integer menuId,
       @RequestParam(value = "restaurantId") Integer restaurantId,
       Model model
   ) {
      List<MenuPosition> menu = menuPositionService.getAllMenuPositions(menuId);
      List<MenuPositionDTO> menuPositions = menu.stream().map(restaurantDtoMapper::mapToDTO).toList();
      model.addAttribute("menuPositions", menuPositions);
      model.addAttribute("restaurantId", restaurantId);
      return "client/menu";
   }

}