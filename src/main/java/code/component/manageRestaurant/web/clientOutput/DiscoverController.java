package code.component.manageRestaurant.web.clientOutput;

import code.component.manageAccount.AccountService;
import code.component.manageRestaurant.manageDelivery.domain.AddressDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class DiscoverController {

   public static final String DISCOVER = "discover";
   private AccountService accountService;

   @GetMapping(DISCOVER)
   public String getDiscover(Model model) {
      model.addAttribute("addressDTO", new AddressDTO());
      String userName = accountService.getAuthenticatedUserName();
      model.addAttribute("clientId", userName);
      return "client/discover";
   }
}