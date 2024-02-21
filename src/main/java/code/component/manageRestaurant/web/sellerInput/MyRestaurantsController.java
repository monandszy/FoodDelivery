package code.component.manageRestaurant.web.sellerInput;

import code.component.manageRestaurant.dao.RestaurantDAO;
import code.component.manageRestaurant.domain.Restaurant;
import code.component.manageRestaurant.domain.RestaurantDTO;
import code.component.manageRestaurant.domain.mapper.DTOMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(MyRestaurantsController.MY_RESTAURANTS)
public class MyRestaurantsController {

   public static final String MY_RESTAURANTS = "myRestaurants";

   RestaurantDAO restaurantDAO;
   DTOMapper dtoMapper;

   @GetMapping("/{sellerCode}")
   public String getRestaurants(
       @PathVariable(value = "sellerCode", required = true) String sellerCode,
       @RequestParam(value = "page") Integer page,
       Model model
   ) {
      List<Restaurant> restaurants = restaurantDAO.getPageByParent(sellerCode, page);
      List<RestaurantDTO> restaurantsPage = restaurants.stream().map(dtoMapper::mapToDTO).toList();
      model.addAttribute("restaurantsPage", restaurantsPage);
      return "myRestaurants";
   }

   void postRestaurant() {

   }

   void deleteRestaurant() {

   }
}