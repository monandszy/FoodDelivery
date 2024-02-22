package code.component.manageAccount.domain.mapper;

import code.component.manageAccount.domain.AccountDetails;
import code.component.manageAccount.domain.AccountDetailsDTO;
import code.component.manageAccount.domain.Role;
import code.component.manageAccount.domain.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountDTOMapper {

   RoleDTO mapToDTO(Role role);

   Role mapFromDTO(RoleDTO roleDTO);

   AccountDetailsDTO mapToDTO(AccountDetails accountDetails);

   AccountDetails mapFromDTO(AccountDetailsDTO accountDetailsDTO);
}