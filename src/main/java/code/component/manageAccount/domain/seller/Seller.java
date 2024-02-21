package code.component.manageAccount.domain.seller;

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
public class Seller {

   Integer id;
}