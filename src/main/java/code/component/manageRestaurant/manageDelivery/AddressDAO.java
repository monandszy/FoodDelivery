package code.component.manageRestaurant.manageDelivery;

import code.component.manageRestaurant.manageDelivery.domain.Address;

import java.util.Optional;

public interface AddressDAO {
   Address addOrFind(Address address);

   Optional<Address> getByIp(String ip);
}