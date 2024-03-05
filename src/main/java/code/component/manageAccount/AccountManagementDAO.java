package code.component.manageAccount;

import code.component.manageAccount.domain.Account;

import java.util.Optional;

public interface AccountManagementDAO {
   Optional<Account> findByUserName(String username);

   void addAccount(Account account);
}