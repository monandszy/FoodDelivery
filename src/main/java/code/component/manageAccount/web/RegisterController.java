package code.component.manageAccount.web;

import code.component.manageAccount.ManageAccountService;
import code.component.manageAccount.domain.AccountDTO;
import code.component.manageAccount.domain.mapper.AccountDTOMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class RegisterController {

   private ManageAccountService manageAccountService;
   private AccountDTOMapper accountDTOMapper;

   public static final String REGISTER = "/register";

   @GetMapping(value = REGISTER)
   public String getRegisterView(Model model) {
      model.addAttribute("account", new AccountDTO());
      return "register";
   }

   // TODO auto-login
   @PostMapping(REGISTER)
   public String processRegister(
       @Valid @ModelAttribute("account") AccountDTO account
   ) {
      manageAccountService.register(accountDTOMapper.mapFromDTO(account));
      return "login";
   }
}