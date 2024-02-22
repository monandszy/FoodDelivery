package code.component.manageAccount.web;

import code.component.manageAccount.ManageAccountService;
import code.component.manageAccount.domain.AccountDTO;
import code.component.manageAccount.domain.RoleDTO;
import code.component.manageAccount.domain.mapper.AccountDTOMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

import static code.component.manageAccount.domain.Role.ACCOUNT_ROLES.ACCOUNT;

@Controller
@AllArgsConstructor
public class RegisterController {

   private ManageAccountService manageAccountService;
   private AccountDTOMapper accountDTOMapper;
   private PasswordEncoder passwordEncoder;

   @GetMapping(value = "/register")
   public String register(Model model) {
      model.addAttribute("account", new AccountDTO());
      return "register";
   }

   @PostMapping(value = "/register")
   public ModelAndView processRegister(
       @Valid @ModelAttribute("account") AccountDTO account
   ) {
      account.setPassword(passwordEncoder.encode(account.getPassword()));
      account.setActive(true);
      account.setRoles(Set.of(RoleDTO.builder().role(ACCOUNT.toString()).build()));
      manageAccountService.register(accountDTOMapper.mapFromDTO(account));
      return new ModelAndView("redirect:/welcome");
   }
}