package code.component.manageAccount.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class LoginController {
   @GetMapping("/login")
   String getLoginView() {
      return "login";
   }
}