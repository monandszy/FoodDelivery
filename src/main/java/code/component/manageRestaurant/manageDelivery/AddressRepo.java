package code.component.manageRestaurant.manageDelivery;

import code.component.manageRestaurant.domain.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class AddressRepo implements AddressDAO {
   @Override
   public void updateAddress(Restaurant restaurant) {

   }

   @Override
   public void updateRange(Restaurant restaurant) {

   }
}