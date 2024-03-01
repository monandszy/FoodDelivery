package code.account.web;

import code.component.manageAccount.data.AccountJpaRepo;
import code.component.manageAccount.data.AccountRepo;
import code.component.manageAccount.domain.Account;
import code.component.manageAccount.domain.mapper.AccountEntityMapperImpl;
import code.configuration.AbstractJpaIT;
import code.util.DataFixtures;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Import(value = {
    AccountRepo.class,
    AccountEntityMapperImpl.class
})
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountRepoIT extends AbstractJpaIT {

   private AccountRepo accountRepo;
   private AccountJpaRepo accountJpaRepo;

   @Test
   @Transactional
   void testAccountCrud() {
//      accountJpaRepo.deleteAll(); // TODO Identity key generation doesn't work correctly in tests :/
//      Account account = DataFixtures.getAccount();
//      accountRepo.addAccount(account);
      String userName = "test";
      List<Account> accountPage = accountRepo.getAccountPage(0);
      Assertions.assertFalse(accountPage.isEmpty());

      accountRepo.setRole(userName, DataFixtures.getSellerRole());
      Optional<Account> byUserName = accountRepo.findByUserName(userName);
      Assertions.assertTrue(byUserName.isPresent());
      Assertions.assertFalse(byUserName.get().getRoles().isEmpty());

      accountRepo.deleteByUserName(userName);
      List<Account> accountPage2 = accountRepo.getAccountPage(0);
      Assertions.assertTrue(accountPage.size() > accountPage2.size());
   }

}