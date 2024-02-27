package code.component.manageRestaurant.manageDelivery;

import code.component.manageRestaurant.domain.Restaurant;
import code.component.manageRestaurant.manageDelivery.domain.Address;

public interface AddressDAO {
   void updateAddress(Restaurant restaurant);

   void updateRange(Restaurant restaurant);

   Address add(Address address);
}