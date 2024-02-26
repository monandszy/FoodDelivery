package code.component.manageRestaurant.manageDelivery;

import code.component.manageRestaurant.domain.Restaurant;
import code.component.manageRestaurant.manageDelivery.domain.Address;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeliveryService {

   private DeliveryServiceDAO deliveryServiceDAO;

   public List<Restaurant> getPageByAddress(Address address, Integer pageNumber) {
      return List.of();
   }
}