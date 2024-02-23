package code.component.manageRestaurant.manageDelivery;

import code.component.manageRestaurant.data.jpa.RestaurantJpaRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class DeliveryRepository implements DeliveryServiceDAO {

   private RestaurantJpaRepo restaurantJpaRepo;

}