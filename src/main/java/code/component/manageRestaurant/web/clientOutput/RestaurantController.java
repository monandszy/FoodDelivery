package code.component.manageRestaurant.web.clientOutput;

import code.component.manageRestaurant.dao.MenuDAO;
import code.component.manageRestaurant.domain.Menu;
import code.component.manageRestaurant.domain.MenuDTO;
import code.component.manageRestaurant.domain.mapper.DTOMapper;
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
@RequestMapping(RestaurantController.RESTAURANT)
public class RestaurantController {

   public static final String RESTAURANT = "restaurant";

   private MenuDAO menuDAO;
   private DTOMapper dtoMapper;

   @GetMapping("/{restaurantId}")
   public String getMenus(
       @PathVariable(value = "restaurantId", required = true) Integer restaurantId,
       @RequestParam(value = "page") Integer page,
       Model model
   ) {
      List<Menu> menus = menuDAO.getPageByParent(restaurantId, page);
      List<MenuDTO> restaurantMenus = menus.stream().map(dtoMapper::mapToDTO).toList();
      model.addAttribute("restaurantPage", restaurantMenus);
      return "client/restaurant";
   }
}