package code.component.manageAccount;

import code.component.manageAccount.domain.AccountDetailsEntity;

public interface AccountManagementDAO {
   AccountDetailsEntity findByUserName();
}