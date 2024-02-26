package code.component.manageRestaurant.web.sellerInput;

import code.component.manageRestaurant.domain.MenuDTO;
import code.component.manageRestaurant.domain.mapper.RestaurantDTOMapper;
import code.component.manageRestaurant.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class SellerRestaurantController {

   public static final String MY_RESTAURANT = "myRestaurant";
   public static final String MY_RESTAURANT_GET = MY_RESTAURANT + "/get/{restaurantId}";
   public static final String MY_RESTAURANT_ADD = MY_RESTAURANT + "/add";
   public static final String MY_RESTAURANT_DELETE = MY_RESTAURANT + "/delete/{menuId}";

   private MenuService menuService;
   private RestaurantDTOMapper dtoMapper;

   @GetMapping(MY_RESTAURANT_GET)
   public String getRestaurantViewById(
       @PathVariable(value = "restaurantId") String restaurantId,
       @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
       Model model
   ) {
      model.addAttribute("menuDTO", MenuDTO.builder().build());
      model.addAttribute("restaurantId", restaurantId);
      pageNumber = Objects.isNull(pageNumber) ? Integer.valueOf(START_PAGE) : pageNumber;
      model.addAttribute("pageNumber", pageNumber);
      List<MenuDTO> restaurantPage = dtoMapper.mapMToDTOList(
          menuService.getPageByRestaurant(Integer.valueOf(restaurantId), pageNumber));
      model.addAttribute("restaurantPage", restaurantPage);
      return "seller/myRestaurant";
   }
   @PostMapping(MY_RESTAURANT_ADD)
   public String postMenu(
       @ModelAttribute("menuDTO") MenuDTO menuDTO,
       @RequestParam("restaurantId") String restaurantId
   ) {
      menuService.add(dtoMapper.mapFromDTO(menuDTO), Integer.valueOf(restaurantId));
      return "redirect:/myRestaurant/get/%s".formatted(restaurantId);
   }

   @DeleteMapping(MY_RESTAURANT_DELETE)
   public String deleteMenu(
       @PathVariable("menuId") String menuId,
       @RequestParam("restaurantId") String restaurantId
   ) {
      menuService.deleteById(Integer.valueOf(menuId));
      return "redirect:/myRestaurant/get/%s".formatted(restaurantId);
   }

}