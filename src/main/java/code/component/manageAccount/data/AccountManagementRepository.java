package code.component.manageAccount.data;

import code.component.manageAccount.AccountManagementDAO;
import code.component.manageAccount.domain.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccountManagementRepository implements AccountManagementDAO {

   private AccountDetailsJpaRepo accountDetailsJpaRepo;
   private RoleJpaRepo roleJpaRepo;

   @Override
   public Optional<Account> findByUserName(String username) {
      return null;
   }

   @Override
   public void addAccount(Account account) {

   }

}