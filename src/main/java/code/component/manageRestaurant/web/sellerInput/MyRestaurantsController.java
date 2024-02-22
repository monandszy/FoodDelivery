package code.component.manageRestaurant.web.sellerInput;

import code.component.manageRestaurant.domain.Restaurant;
import code.component.manageRestaurant.domain.RestaurantDTO;
import code.component.manageRestaurant.domain.mapper.RestaurantDTOMapper;
import code.component.manageRestaurant.service.RestaurantService;
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
public class MyRestaurantsController {

   public static final String MY_RESTAURANTS = "myRestaurants";

   private RestaurantService restaurantService;
   private RestaurantDTOMapper restaurantDtoMapper;

   @GetMapping(MY_RESTAURANTS + "/{sellerId}")
   public String getRestaurantsViewBySellerId(
       @PathVariable(value = "sellerId") String sellerId,
       @RequestParam(value = "pageNumber") Integer pageNumber,
       Model model
   ) {
      pageNumber = Objects.isNull(pageNumber) ? Integer.valueOf(1) : pageNumber;
      List<Restaurant> restaurants = restaurantService.getPageByParent(sellerId, pageNumber);
      List<RestaurantDTO> restaurantsPage = restaurants.stream().map(restaurantDtoMapper::mapToDTO).toList();
      model.addAttribute("restaurantsPage", restaurantsPage);
      model.addAttribute("sellerId", sellerId);
      model.addAttribute("pageNumber", pageNumber);
      return "seller/myRestaurants";
   }

   @PostMapping(MY_RESTAURANTS + "/add")
   public String postRestaurant(
       @ModelAttribute("restaurantDTO") RestaurantDTO restaurantDTO
   ) {
      return "redirect:seller/myRestaurant";
   }

   @PostMapping(MY_RESTAURANTS + "/delete")
   public String deleteRestaurant(
       @ModelAttribute("restaurantDTO") RestaurantDTO restaurantDTO
   ) {
      return "redirect:seller/myRestaurant";
   }
}