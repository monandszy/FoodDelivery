package code.component.manageAccount.domain.mapper;

import code.component.manageAccount.domain.Account;
import code.component.manageAccount.domain.AccountDTO;
import code.component.manageAccount.domain.Role;
import code.component.manageAccount.domain.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountDTOMapper {

   RoleDTO mapToDTO(Role role);

   Role mapFromDTO(RoleDTO roleDTO);

   AccountDTO mapToDTO(Account account);

   @Mapping(target = "roles", ignore = true)
   Account mapFromDTO(AccountDTO accountDTO);
}