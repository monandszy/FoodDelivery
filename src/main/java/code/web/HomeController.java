package code.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

   public static final String home = "/home";

   @GetMapping(home)
   public String redirect() {
      return "home";
   }
}