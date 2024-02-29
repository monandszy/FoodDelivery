package code.component.manageRestaurant.manageDelivery;

import code.component.manageRestaurant.manageDelivery.domain.Address;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class AddressRepo implements AddressDAO {

   @Override
   public Address addOrFind(Address address) {
      return Address.builder().id(1).build();
   }

   @Override
   public Optional<Address> getByIp(String ip) {
      return Optional.of(Address.builder().id(1).build());
   }
}