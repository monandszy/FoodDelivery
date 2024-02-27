package code.component.manageRestaurant.web.clientOutput;

import code.component.manageAccount.AccountService;
import code.component.manageRestaurant.domain.RestaurantDTO;
import code.component.manageRestaurant.domain.mapper.RestaurantDTOMapper;
import code.component.manageRestaurant.manageDelivery.DeliveryService;
import code.component.manageRestaurant.manageDelivery.domain.AddressDTO;
import code.component.manageRestaurant.manageDelivery.domain.AddressDTOMapper;
import code.component.manageRestaurant.service.RestaurantService;
import code.configuration.Constants;
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
   private RestaurantDTOMapper dtoMapper;
   private AddressDTOMapper addressDTOMapper;
   private AccountService accountService;
   private RestaurantService restaurantService;

   @GetMapping(RESTAURANTS_GET)
   public String getRestaurantsByAddress(
       @ModelAttribute(value = "addressDTO") AddressDTO addressDTO,
       @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
       HttpSession session,
       Model model
   ) {
      AddressDTO TEMP = AddressDTO.builder().id(1).build();
      session.setAttribute(Constants.ADDRESS, TEMP);
      model.addAttribute("addressDTO", addressDTO);
      pageNumber = Objects.isNull(pageNumber) ? Integer.valueOf(START_PAGE) : pageNumber;
      model.addAttribute("pageNumber", pageNumber);
//      List<RestaurantDTO> restaurantsPage = dtoMapper.mapRToDTOList(deliveryService.
//          getPageByAddress(addressDTOMapper.mapFromDTO(addressDTO), pageNumber));

      // TODO address API integration
      String sellerId = accountService.getAuthenticatedUserName();
      List<RestaurantDTO> restaurantsPage = dtoMapper.mapRToDTOList(restaurantService.getPageBySellerId(sellerId, pageNumber));
      session.setAttribute(Constants.RESTAURANT_PAGE, restaurantsPage);
      return "redirect:/discover";
   }
}