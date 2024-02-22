package code.component.manageAccount.data;

import code.component.manageAccount.domain.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDetailsJpaRepo extends JpaRepository<AccountEntity, Integer> {
}