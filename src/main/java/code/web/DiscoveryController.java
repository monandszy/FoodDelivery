package code.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class DiscoveryController {

   public static final String DISCOVERY = "discovery";

   @GetMapping(DISCOVERY)
   String getDiscover() {
      return "client/discover";
   }
}