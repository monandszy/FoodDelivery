package code.component.manageAccount.domain;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;
import org.springframework.lang.NonNull;

import java.util.Set;

@With
@Data
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

   private Integer id;
   @NonNull
   @NotEmpty
   private String userName;
   @NonNull
   @NotEmpty
   private String password;
   @NonNull
   private Boolean active;
   private Set<RoleDTO> roles;
}