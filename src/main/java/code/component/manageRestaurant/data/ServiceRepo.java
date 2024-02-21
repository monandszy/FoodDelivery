package code.component.manageRestaurant.data;

import code.component.manageRestaurant.dao.ServiceDAO;
import code.component.manageRestaurant.domain.Restaurant;

import java.util.List;

public class ServiceRepo implements ServiceDAO {

   @Override
   public List<Restaurant> getRestaurantsByAddressProximity() {
      return null;
   }
}