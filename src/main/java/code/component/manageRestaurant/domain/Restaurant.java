package code.component.manageRestaurant.domain;

import code.component.manageAccount.domain.Account;
import code.component.manageRestaurant.manageDelivery.domain.Address;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.With;

import java.math.BigInteger;
import java.util.List;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
public class Restaurant {

   Integer id;
   Address address;
   Account seller;
   BigInteger deliveryRange;
   List<Menu> menus;
}