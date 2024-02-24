package code.component.manageRestaurant.web.clientOutput;

import code.component.manageRestaurant.domain.Restaurant;
import code.component.manageRestaurant.domain.RestaurantDTO;
import code.component.manageRestaurant.domain.mapper.RestaurantDTOMapper;
import code.component.manageRestaurant.manageDelivery.DeliveryService;
import code.component.manageRestaurant.manageDelivery.domain.AddressDTO;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

import static code.configuration.Constants.START_PAGE;


@Controller
@AllArgsConstructor
public class RestaurantsController {

   public static final String RESTAURANTS = "restaurants";
   public static final String RESTAURANTS_GET = RESTAURANTS + "/getByAddress";

   private DeliveryService deliveryService;
   private RestaurantDTOMapper restaurantDtoMapper;

   @GetMapping(RESTAURANTS_GET)
   String getRestaurantsViewByAddress(
       @ModelAttribute(value = "address") AddressDTO addressDTO,
       @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
       HttpSession session,
       Model model
   ) {
      session.setAttribute("ADDRESS", addressDTO);
      model.addAttribute("addressDTO", addressDTO);
      pageNumber = Objects.isNull(pageNumber) ? Integer.valueOf(START_PAGE) : pageNumber;
      model.addAttribute("pageNumber", pageNumber);
      List<Restaurant> restaurants = deliveryService.getPageByAddress(addressDTO, pageNumber);
      List<RestaurantDTO> restaurantsPage = restaurants.stream().map(restaurantDtoMapper::mapToDTO).toList();
      model.addAttribute("restaurantsByAddressPage", restaurantsPage);
      return "client/discover";
   }
}