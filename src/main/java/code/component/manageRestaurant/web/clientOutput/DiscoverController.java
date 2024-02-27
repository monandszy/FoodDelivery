package code.component.manageRestaurant.web.clientOutput;

import code.component.manageAccount.AccountService;
import code.component.manageRestaurant.manageDelivery.domain.AddressDTO;
import code.configuration.Constants;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@Controller
@AllArgsConstructor
public class DiscoverController {

   public static final String DISCOVER = "discover";
   private AccountService accountService;

   @GetMapping(DISCOVER)
   public String getDiscover(Model model, HttpSession session) {
      Object page = session.getAttribute(Constants.RESTAURANT_PAGE);
      if (Objects.nonNull(page))
         model.addAttribute("restaurantsByAddressPage", page);

      model.addAttribute(Constants.USERNAME, accountService.getAuthenticatedUserName());
      model.addAttribute("addressDTO", new AddressDTO());
      return "client/discover";
   }
}