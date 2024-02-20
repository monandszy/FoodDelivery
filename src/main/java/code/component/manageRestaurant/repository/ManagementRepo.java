package code.component.manageRestaurant.repository;

import code.component.manageRestaurant.dao.ManagementDAO;
import code.component.manageRestaurant.domain.Restaurant;

import java.util.List;

public class ManagementRepo implements ManagementDAO {

   @Override
   public List<Restaurant> getRestaurantsByAddressProximity() {
      return null;
   }
}