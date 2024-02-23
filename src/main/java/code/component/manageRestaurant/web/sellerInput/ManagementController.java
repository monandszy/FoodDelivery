package code.component.manageRestaurant.web.sellerInput;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagementController {

   public static final String MANAGEMENT = "/manage";

   @GetMapping(MANAGEMENT)
   public String redirect(Model model) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      model.addAttribute("sellerId", authentication.getName());
      return "seller/manage";
   }
}