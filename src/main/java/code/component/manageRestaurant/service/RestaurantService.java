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

   public void add(Restaurant restaurant) {
      restaurantDAO.add(restaurant);
   }

   public List<Restaurant> getPageBySellerId(Object sellerId, Integer page) {
      return restaurantDAO.getPageByParent(sellerId, page);
   }

   public void deleteById(Integer id) {
      restaurantDAO.deleteById(id);
   }
}