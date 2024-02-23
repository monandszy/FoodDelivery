package code.component.manageRestaurant.manageDelivery;

import code.component.manageRestaurant.domain.Restaurant;

public interface AddressDAO {
   void updateAddress(Restaurant restaurant);

   void updateRange(Restaurant restaurant);

}