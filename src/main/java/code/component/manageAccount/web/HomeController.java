package code.component.manageAccount.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {

   public static final String HOME = "/home";

   @GetMapping(HOME)
   public String redirect() {
      return "home";
   }

   // TODO ADD ADMINISTRATION FUNCTIONS
//   @GetMapping
//   void getRoles() {
//
//   }
//
//   @GetMapping
//   void getAccounts() {
//
//   }
//
//   @PutMapping
//   void enableAccount() {
//
//   }
//
//   @PutMapping()
//   void setRole() {
//
//   }
//
//   @DeleteMapping()
//   void deleteAccount() {
//
//   }
}