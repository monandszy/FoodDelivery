package code.component.manageAccount.domain.mapper;

import code.component.manageAccount.domain.AccountDetails;
import code.component.manageAccount.domain.AccountDetailsEntity;
import code.component.manageAccount.domain.Role;
import code.component.manageAccount.domain.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountEntityMapper {

   RoleEntity mapToEntity(Role role);

   Role mapFromEntity(RoleEntity roleEntity);

   AccountDetailsEntity mapToEntity(AccountDetails AccountDetails);

   AccountDetails mapFromEntity(AccountDetailsEntity AccountDetailsEntity);
}