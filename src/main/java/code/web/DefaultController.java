package code.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

   public static final String DEFAULT = "/";

   @GetMapping(DEFAULT)
   public String redirect() {
      return "client/discover";
   }
}