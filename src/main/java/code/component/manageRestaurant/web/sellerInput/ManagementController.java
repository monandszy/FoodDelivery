package code.component.manageRestaurant.web.sellerInput;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagementController {

   public static final String MANAGEMENT = "/management";

   @GetMapping(MANAGEMENT)
   public String redirect(Model model) {
      model.addAttribute("sellerId", 1); // TEMP SELLER ID - GET FROM SESSION
      return "management";
   }
}