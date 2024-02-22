package code.component.manageAccount.data;

import code.component.manageAccount.AccountManagementDAO;
import code.component.manageAccount.domain.AccountDetailsEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class AccountManagementRepository implements AccountManagementDAO {

   private AccountDetailsJpaRepo accountDetailsJpaRepo;

   @Override
   public AccountDetailsEntity findByUserName() {
      return null;
   }
}