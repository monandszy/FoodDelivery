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

import static code.configuration.Constants.START_PAGE;

@Controller
@AllArgsConstructor
public class MyRestaurantController {

   public static final String MY_RESTAURANT = "myRestaurant";

   private MenuService menuService;
   private RestaurantDTOMapper dtoMapper;

   @GetMapping(MY_RESTAURANT + "/get/{restaurantId}")
   public String getRestaurantViewById(
       @PathVariable(value = "restaurantId") Integer restaurantId,
       @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
       Model model
   ) {
      model.addAttribute("menuDTO", MenuDTO.builder().build());
      model.addAttribute("restaurantId", restaurantId);
      pageNumber = Objects.isNull(pageNumber) ? Integer.valueOf(START_PAGE) : pageNumber;
      model.addAttribute("pageNumber", pageNumber);
      List<Menu> menus = menuService.getPageByRestaurant(restaurantId, pageNumber);
      List<MenuDTO> restaurantMenus = menus.stream().map(dtoMapper::mapToDTO).toList();
      model.addAttribute("restaurantPage", restaurantMenus);
      return "seller/myRestaurant";
   }
   @PostMapping(MY_RESTAURANT + "/add")
   public String postMenu(
       @ModelAttribute("menuDTO") MenuDTO menuDTO,
       @RequestParam("restaurantId") Integer restaurantId
   ) {
      menuService.add(dtoMapper.mapFromDTO(menuDTO), restaurantId);
      return "redirect:/myRestaurant/get/%s".formatted(restaurantId);
   }

   @PostMapping(MY_RESTAURANT + "/delete/{menuId}")
   public String deleteMenu(
       @PathVariable("menuId") Integer menuId,
       @RequestParam("restaurantId") Integer restaurantId
   ) {
      menuService.deleteById(menuId);
      return "redirect:/myRestaurant/get/%s".formatted(restaurantId);
   }

}