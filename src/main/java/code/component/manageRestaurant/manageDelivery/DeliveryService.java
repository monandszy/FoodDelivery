package code.component.manageRestaurant.manageDelivery;

import code.component.manageRestaurant.domain.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeliveryService {

   private DeliveryServiceDAO deliveryServiceDAO;

   public List<Restaurant> getPageByAddress(Object addressDTO, Integer pageNumber) {
      return List.of();
   }
}