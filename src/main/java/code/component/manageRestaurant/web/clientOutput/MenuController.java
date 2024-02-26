package code.component.manageRestaurant.web.clientOutput;

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
   public static final String MENU_GET = MENU + "/get/{menuId}";

   private MenuPositionService menuPositionService;
   private RestaurantDTOMapper dtoMapper;

   @GetMapping(MENU_GET)
   public String getMenuPositions(
       @PathVariable(value = "menuId") String menuId,
       @RequestParam(value = "restaurantId") String restaurantId,
       Model model
   ) {
      model.addAttribute("restaurantId", restaurantId);
      List<MenuPositionDTO> menuPositions = dtoMapper.mapMPToDTOList(
          menuPositionService.getAllMenuPositions(Integer.valueOf(menuId)));
      model.addAttribute("menuPositions", menuPositions);
      return "client/menu";
   }

}