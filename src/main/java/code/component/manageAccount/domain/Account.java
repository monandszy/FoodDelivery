package code.component.manageAccount.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.With;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = {})
@ToString(of = {})
public class Account {

   Integer id;
   String userName;
   String password;
   Boolean active;
   Set<Role> roles;

}