package code.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagementController {

   public static final String MANAGEMENT = "/management";

   @GetMapping(MANAGEMENT)
   public String redirect() {
      return "management";
   }
}