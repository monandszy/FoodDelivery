package code.component.manageAccount.data;

import code.component.manageAccount.AccountDAO;
import code.component.manageAccount.domain.Account;
import code.component.manageAccount.domain.AccountEntity;
import code.component.manageAccount.domain.Role;
import code.component.manageAccount.domain.RoleEntity;
import code.component.manageAccount.domain.mapper.AccountEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static code.configuration.Constants.PAGE_SIZE;

@Repository
@AllArgsConstructor
public class AccountRepo implements AccountDAO {

   private AccountEntityMapper entityMapper;
   private AccountJpaRepo accountJpaRepo;
   private RoleJpaRepo roleJpaRepo;

   @Override
   public Optional<Account> findByUserName(String username) {
      Optional<AccountEntity> byUserName = accountJpaRepo.findByUserName(username);
      return byUserName.map(e -> entityMapper.mapFromEntity(e));
   }

   @Override
   public void addAccount(Account account, Role role) {
      RoleEntity accountRole = roleJpaRepo.findByRole(role.getRole()).orElseThrow();
      AccountEntity accountEntity = entityMapper.mapToEntity(account);
      accountEntity.setRoles(Set.of(accountRole));
      accountJpaRepo.save(accountEntity);
   }

   @Override
   public List<Account> getAccountPage(Integer pageNumber) {
      Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("roles"));
      Page<AccountEntity> pageBySeller = accountJpaRepo.findAll(pageable);
      return pageBySeller.getContent().stream().map(entityMapper::mapFromEntity).toList();
   }

   @Override
   public void deleteByUserName(String userName) {
      accountJpaRepo.delete(entityMapper.mapToEntity(
          findByUserName(userName).orElseThrow()));
   }

   @Override
   public void setRole(String userName, Role role) {
      AccountEntity accountEntity = entityMapper.mapToEntity(
          findByUserName(userName).orElseThrow());
      Set<RoleEntity> roles = accountEntity.getRoles();
      roles.add(roleJpaRepo.findByRole(role.getRole()).orElseThrow());
      accountJpaRepo.save(accountEntity);
   }

}