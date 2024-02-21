package code.component.manageAccount.domain.client;

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
public class Client {

   Integer id;
   String email;
}