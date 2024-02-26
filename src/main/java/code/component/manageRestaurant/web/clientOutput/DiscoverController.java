package code.component.manageRestaurant.web.clientOutput;

import code.component.manageRestaurant.manageDelivery.domain.AddressDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiscoverController {

   public static final String DISCOVER = "discover";

   @GetMapping(DISCOVER)
   public String getDiscover(Model model) {
      model.addAttribute("addressDTO", new AddressDTO());
      return "client/discover";
   }
}