package code.component.manageRestaurant.web.clientOutput;

import code.component.manageRestaurant.domain.Restaurant;
import code.component.manageRestaurant.domain.RestaurantDTO;
import code.component.manageRestaurant.domain.mapper.DTOMapper;
import code.component.manageRestaurant.service.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping(RestaurantsController.RESTAURANTS)
public class RestaurantsController {

   public static final String RESTAURANTS = "restaurants";

   private RestaurantService restaurantService;
   private DTOMapper dtoMapper;

   @GetMapping
   String getRestaurants(
       @RequestParam("page") Integer page,
       Model model
   ) {
      List<Restaurant> restaurants = restaurantService.getPage(page);
      List<RestaurantDTO> restaurantsPage = restaurants.stream().map(dtoMapper::mapToDTO).toList();
      model.addAttribute("restaurantsPage", restaurantsPage);
      return "client/discover";
   }
}