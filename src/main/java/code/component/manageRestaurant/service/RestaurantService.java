package code.component.manageRestaurant.service;

import code.component.manageRestaurant.dao.RestaurantDAO;
import code.component.manageRestaurant.domain.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService {
   private RestaurantDAO restaurantDAO;
   private ProximityCalculationService proximityCalculationService;

   public void add(Restaurant restaurant) {
      restaurantDAO.add(restaurant);
   }

   public List<Restaurant> getPageByAddress(Object address, Integer page) {
      return proximityCalculationService.getRestaurantsBasedOnProximity(address);
   }

   public List<Restaurant> getPageByParent(Object parentKey, Integer page) {
      return restaurantDAO.getPageByParent(parentKey, page);
   }

   public List<Restaurant> getPage(Integer page) {
      return restaurantDAO.getPage(page);
   }

   public void deleteById(Integer id) {
      restaurantDAO.deleteById(id);
   }

   public void updateAddress(Restaurant restaurant) {

   }

   public void updateRange(Restaurant restaurant) {

   }
}