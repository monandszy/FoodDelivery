package code.component.manageRestaurant.manageDelivery;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
public class Address {

   Integer id;

}