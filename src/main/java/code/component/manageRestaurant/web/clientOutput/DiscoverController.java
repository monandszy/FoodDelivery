package code.component.manageRestaurant.web.clientOutput;

import code.component.manageAccount.AccountService;
import code.component.manageRestaurant.domain.RestaurantDTO;
import code.component.manageRestaurant.domain.mapper.RestaurantDTOMapper;
import code.component.manageRestaurant.manageDelivery.AddressService;
import code.component.manageRestaurant.manageDelivery.domain.AddressDTO;
import code.component.manageRestaurant.manageDelivery.domain.AddressDTOMapper;
import code.configuration.Constants;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

import static code.configuration.Constants.START_PAGE;

@Controller
@AllArgsConstructor
public class DiscoverController {

   public static final String DISCOVER = "discover";
   private AddressService addressService;
   private RestaurantDTOMapper dtoMapper;
   private AddressDTOMapper addressDTOMapper;
   private AccountService accountService;

   @GetMapping(DISCOVER)
   public String getRestaurantsByIp(
       @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
       @RequestParam(value = "ip", required = false) String ip,
       Model model,
       HttpSession session
   ) {

      model.addAttribute(Constants.USERNAME, accountService.getAuthenticatedUserName());
      pageNumber = Objects.isNull(pageNumber) ? Integer.valueOf(START_PAGE) : pageNumber;
      model.addAttribute("pageNumber", pageNumber);

      AddressDTO address = (AddressDTO) session.getAttribute(Constants.ADDRESS);
      if (Objects.isNull(address) || Objects.nonNull(ip)) {
         address = addressService.getAddress(ip);
         session.setAttribute(Constants.ADDRESS, address);
      }
      List<RestaurantDTO> restaurantPage = dtoMapper.mapRToDTOList(
          addressService.getPageByAddress(addressDTOMapper.mapFromDTO(address), pageNumber));
      model.addAttribute("restaurantsByAddressPage", restaurantPage);

      return "client/discover";
   }

}