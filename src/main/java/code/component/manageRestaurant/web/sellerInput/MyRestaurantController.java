package code.component.manageRestaurant.web.sellerInput;

import code.component.manageRestaurant.domain.Menu;
import code.component.manageRestaurant.domain.MenuDTO;
import code.component.manageRestaurant.domain.mapper.RestaurantDTOMapper;
import code.component.manageRestaurant.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
@AllArgsConstructor
public class MyRestaurantController {

   public static final String MY_RESTAURANT = "myRestaurant";

   private MenuService menuService;
   private RestaurantDTOMapper restaurantDtoMapper;

   @GetMapping(MY_RESTAURANT + "/{restaurantId}")
   public String getRestaurantViewById(
       @PathVariable(value = "restaurantId") Integer restaurantId,
       @RequestParam(value = "pageNumber") Integer pageNumber,
       Model model
   ) {
      pageNumber = Objects.isNull(pageNumber) ? Integer.valueOf(1) : pageNumber;
      List<Menu> menus = menuService.getPageByParent(restaurantId, pageNumber);
      List<MenuDTO> restaurantMenus = menus.stream().map(restaurantDtoMapper::mapToDTO).toList();
      model.addAttribute("restaurantPage", restaurantMenus);
      model.addAttribute("restaurantId", restaurantId);
      model.addAttribute("pageNumber", pageNumber);
      return "seller/myRestaurant";
   }
   @PostMapping(MY_RESTAURANT + "/add")
   public String postMenu(
       @ModelAttribute("menuDTO") MenuDTO menuDTO
   ) {
      return "redirect:seller/myRestaurant";
   }

   @PostMapping(MY_RESTAURANT + "/delete")
   public String deleteMenu(
       @ModelAttribute("menuDTO") MenuDTO menuDTO
   ) {
      return "redirect:seller/myRestaurant";
   }

}