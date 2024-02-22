package code.component.manageAccount;

import code.component.manageAccount.domain.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ManageAccountService {

   private AccountManagementDAO managementDAO;

   @Transactional
   public void register(Account account) {
      managementDAO.findByUserName(account.getUserName()).ifPresent(e -> {throw new RuntimeException("Account with that Username already exists");});
      managementDAO.addAccount(account);
   }
}