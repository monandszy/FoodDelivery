package code.component.manageRestaurant.web.clientOutput;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class DiscoverController {

   public static final String DISCOVERY = "discover";

   @GetMapping(DISCOVERY)
   public String getDiscover() {
      return "client/discover";
   }
}