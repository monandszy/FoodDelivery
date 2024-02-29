package code.component.manageRestaurant.manageDelivery;

import code.component.manageRestaurant.manageDelivery.domain.Address;

import java.util.Optional;

public interface AddressDAO {
   void updateAddress(Address address, Integer restaurantId);

   void updateRange(Integer range, Integer restaurantId);

   Address add(Address address);

   Optional<Address> getByIp(String ip);
}