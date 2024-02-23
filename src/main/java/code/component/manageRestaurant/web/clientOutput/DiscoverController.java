package code.component.manageRestaurant.web.clientOutput;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class DiscoverController {

   public static final String DISCOVERY = "discover";

   @GetMapping(DISCOVERY)
   public String getDiscover(Model model) {
      model.addAttribute("addressDTO", new Object());
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      model.addAttribute("clientId", authentication.getName());
      return "client/discover";
   }
}