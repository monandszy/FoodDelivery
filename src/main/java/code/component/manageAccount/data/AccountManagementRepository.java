package code.component.manageAccount.data;

import code.component.manageAccount.AccountManagementDAO;
import code.component.manageAccount.domain.Account;
import code.component.manageAccount.domain.AccountEntity;
import code.component.manageAccount.domain.mapper.AccountEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class AccountManagementRepository implements AccountManagementDAO {

   private AccountEntityMapper entityMapper;
   private AccountJpaRepo accountJpaRepo;

   @Override
   public Optional<Account> findByUserName(String username) {
      Optional<AccountEntity> byUserName = accountJpaRepo.findByUserName(username);
      return byUserName.map(e -> entityMapper.mapFromEntity(e).withRoles(
          e.getRoles().stream().map(entityMapper::mapFromEntity)
              .collect(Collectors.toSet()))
      );
   }

   @Override
   public void addAccount(Account account) {
      AccountEntity accountEntity = entityMapper.mapToEntity(account);
      accountEntity.setRoles(account.getRoles().stream()
          .map(entityMapper::mapToEntity).collect(Collectors.toSet()));
      accountJpaRepo.save(accountEntity);
   }

}