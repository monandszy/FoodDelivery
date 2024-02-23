package code.component.manageRestaurant.web.clientOutput;

import code.component.manageRestaurant.domain.Menu;
import code.component.manageRestaurant.domain.MenuDTO;
import code.component.manageRestaurant.domain.mapper.RestaurantDTOMapper;
import code.component.manageRestaurant.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
@AllArgsConstructor
public class RestaurantController {

   public static final String RESTAURANT = "restaurant";

   private MenuService menuService;
   private RestaurantDTOMapper restaurantDtoMapper;

   @GetMapping(RESTAURANT + "/get/{restaurantId}")
   public String getMenusView(
       @PathVariable(value = "restaurantId") Integer restaurantId,
       @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
       Model model
   ) {
      model.addAttribute("restaurantId", restaurantId);
      pageNumber = Objects.isNull(pageNumber) ? Integer.valueOf(1) : pageNumber;
      model.addAttribute("pageNumber", pageNumber);
      List<Menu> menus = menuService.getPageByRestaurantId(restaurantId, pageNumber);
      List<MenuDTO> restaurantMenus = menus.stream().map(restaurantDtoMapper::mapToDTO).toList();
      model.addAttribute("restaurantPage", restaurantMenus);
      return "client/restaurant";
   }
}