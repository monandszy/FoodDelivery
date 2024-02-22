package code.component.manageAccount.data;

import code.component.manageAccount.domain.AccountDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDetailsJpaRepo extends JpaRepository<AccountDetailsEntity, Integer> {
}