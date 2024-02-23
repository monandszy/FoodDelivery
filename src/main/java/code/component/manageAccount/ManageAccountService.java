package code.component.manageAccount;

import code.component.manageAccount.domain.Account;
import code.component.manageAccount.domain.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static code.component.manageAccount.domain.Role.ACCOUNT_ROLE.ACCOUNT;

@Service
@AllArgsConstructor
public class ManageAccountService {

   private AccountManagementDAO managementDAO;
   private PasswordEncoder passwordEncoder;

   @Transactional
   public void register(Account account) {
      managementDAO.findByUserName(account.getUserName()).ifPresent(e -> {throw new RuntimeException("Account with that Username already exists");});
      managementDAO.addAccount(account
          .withPassword(passwordEncoder.encode(account.getPassword()))
          .withActive(true)
          .withRoles(Set.of(Role.builder().role(ACCOUNT).build())));
   }
}