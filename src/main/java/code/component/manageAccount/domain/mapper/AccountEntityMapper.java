package code.component.manageAccount.domain.mapper;

import code.component.manageAccount.domain.Account;
import code.component.manageAccount.domain.AccountEntity;
import code.component.manageAccount.domain.Role;
import code.component.manageAccount.domain.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountEntityMapper {

   RoleEntity mapToEntity(Role role);

   Role mapFromEntity(RoleEntity roleEntity);

   @Mapping(target = "roles", source = "roles", ignore = true)
   AccountEntity mapToEntity(Account Account);

   @Mapping(target = "roles", source = "roles", ignore = true)
   Account mapFromEntity(AccountEntity AccountEntity);
}