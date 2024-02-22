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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping(MenuController.MENU)
public class MenuController {

   public static final String MENU = "menu";

   private MenuPositionService menuPositionService;
   private RestaurantDTOMapper restaurantDtoMapper;

   @GetMapping("/{menuId}")
   public String getMenuPositions(
       @PathVariable(value = "menuId", required = true) Integer menuId,
       @RequestParam(value = "page") Integer page,
       Model model
   ) {
      List<MenuPosition> menu = menuPositionService.getPageByParent(menuId, page);
      List<MenuPositionDTO> menuPage = menu.stream().map(restaurantDtoMapper::mapToDTO).toList();
      model.addAttribute("menuPage", menuPage);
      return "client/menu";
   }

}