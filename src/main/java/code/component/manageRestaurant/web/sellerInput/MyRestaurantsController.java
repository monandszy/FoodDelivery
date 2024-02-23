package code.component.manageRestaurant.web.sellerInput;

import code.component.manageAccount.UserAccountDetailsService;
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

import static code.configuration.Constants.START_PAGE;

@Controller
@AllArgsConstructor
public class MyRestaurantsController {

   public static final String MY_RESTAURANTS = "myRestaurants";

   private RestaurantService restaurantService;
   private UserAccountDetailsService accountService;
   private RestaurantDTOMapper dtoMapper;

   @GetMapping(MY_RESTAURANTS + "/get")
   public String getRestaurantsViewBySellerId(
       @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
       Model model
   ) {
      model.addAttribute("restaurantDTO", RestaurantDTO.builder().build());
      pageNumber = Objects.isNull(pageNumber) ? Integer.valueOf(START_PAGE) : pageNumber;
      model.addAttribute("pageNumber", pageNumber);
      String sellerId = accountService.getAuthenticatedUserName();
      model.addAttribute("sellerId", sellerId);
      List<Restaurant> restaurants = restaurantService.getPageBySellerId(sellerId, pageNumber);
      List<RestaurantDTO> restaurantsPage = restaurants.stream().map(dtoMapper::mapToDTO).toList();
      model.addAttribute("restaurantsPage", restaurantsPage);
      return "seller/myRestaurants";
   }

   @PostMapping(MY_RESTAURANTS + "/add")
   public String postRestaurant(
       @ModelAttribute("restaurantDTO") RestaurantDTO restaurantDTO
   ) {
      String sellerId = accountService.getAuthenticatedUserName();
      restaurantService.add(
          dtoMapper.mapFromDTO(restaurantDTO),
          sellerId);
      return "redirect:/myRestaurants/get";
   }

   @PostMapping(MY_RESTAURANTS + "/delete/{restaurantId}")
   public String deleteRestaurant(
       @PathVariable("restaurantId") Integer restaurantId
   ) {
      restaurantService.deleteById(restaurantId);
      return "redirect:/myRestaurants/get";
   }
}