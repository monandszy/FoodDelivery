package code.service.manageRestaurant.repository;

import code.service.manageRestaurant.dao.ManagementDAO;
import code.service.manageRestaurant.domain.Restaurant;

import java.util.List;

public class ManagementRepo implements ManagementDAO {

   @Override
   public List<Restaurant> getRestaurantsByAddressProximity() {
      return null;
   }
}