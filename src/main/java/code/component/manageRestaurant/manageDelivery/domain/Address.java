package code.component.manageRestaurant.manageDelivery.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
public class Address {

   Integer id;
   String city;
   String postalCode;
   String ipAddress;
   BigDecimal latitude;
   BigDecimal longitude;

}