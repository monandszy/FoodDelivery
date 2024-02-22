package code.component.manageRestaurant.web.clientOutput;

import code.component.manageRestaurant.domain.Restaurant;
import code.component.manageRestaurant.domain.RestaurantDTO;
import code.component.manageRestaurant.domain.mapper.RestaurantDTOMapper;
import code.component.manageRestaurant.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@AllArgsConstructor
public class RestaurantsController {

   public static final String RESTAURANTS = "restaurants";

   private RestaurantService restaurantService;
   private RestaurantDTOMapper restaurantDtoMapper;

   @GetMapping(RESTAURANTS)
   String getRestaurantsView(
       @RequestParam("page") Integer page,
       Model model
   ) {
      List<Restaurant> restaurants = restaurantService.getPage(page);
      List<RestaurantDTO> restaurantsPage = restaurants.stream().map(restaurantDtoMapper::mapToDTO).toList();
      model.addAttribute("restaurantsPage", restaurantsPage);
      return "client/discover";
   }
}