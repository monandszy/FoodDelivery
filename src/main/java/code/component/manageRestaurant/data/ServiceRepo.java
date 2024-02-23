package code.component.manageRestaurant.data;

import code.component.manageRestaurant.dao.ServiceDAO;
import code.component.manageRestaurant.domain.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServiceRepo implements ServiceDAO {

   @Override
   public List<Restaurant> getRestaurantsByAddressProximity() {
      return List.of();
   }
}