package code.component.manageAccount.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
@EqualsAndHashCode(of = {})
@ToString(of = {})
public class AccountDetails {

   Integer id;
   String code;

}