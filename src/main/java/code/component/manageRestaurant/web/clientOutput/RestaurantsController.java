package code.component.manageRestaurant.web.clientOutput;

import code.component.manageRestaurant.domain.Restaurant;
import code.component.manageRestaurant.domain.RestaurantDTO;
import code.component.manageRestaurant.domain.mapper.RestaurantDTOMapper;
import code.component.manageRestaurant.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;


@Controller
@AllArgsConstructor
public class RestaurantsController {

   public static final String RESTAURANTS = "restaurants";

   private RestaurantService restaurantService;
   private RestaurantDTOMapper restaurantDtoMapper;

   @GetMapping(RESTAURANTS + "/getByAddress")
   String getRestaurantsViewByAddress(
       @ModelAttribute(value = "address") Object addressDTO,
       @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
       Model model
   ) {
      model.addAttribute("addressDTO", addressDTO);
      pageNumber = Objects.isNull(pageNumber) ? Integer.valueOf(1) : pageNumber;
      model.addAttribute("pageNumber", pageNumber);
      List<Restaurant> restaurants = restaurantService.getPageByAddress(addressDTO, pageNumber);
      List<RestaurantDTO> restaurantsPage = restaurants.stream().map(restaurantDtoMapper::mapToDTO).toList();
      model.addAttribute("restaurantsByAddressPage", restaurantsPage);
      return "client/discover";
   }
}