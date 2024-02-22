package code.component.manageAccount.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.Set;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

   private Integer id;
   private String userName;
   private String password;
   private Boolean active;
   private Set<RoleDTO> roles;
}