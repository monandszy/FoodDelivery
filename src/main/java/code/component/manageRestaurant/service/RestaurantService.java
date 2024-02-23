package code.component.manageRestaurant.service;

import code.component.manageRestaurant.dao.RestaurantDAO;
import code.component.manageRestaurant.domain.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService {
   private RestaurantDAO restaurantDAO;

   @Transactional
   public void add(Restaurant restaurant, String sellerId) {
      restaurantDAO.add(restaurant, sellerId);
   }

   @Transactional
   public List<Restaurant> getPageBySellerId(String sellerId, Integer page) {
      return restaurantDAO.getPageBySeller(sellerId, page);
   }

   @Transactional
   public void deleteById(Integer id) {
      restaurantDAO.deleteById(id);
   }
}