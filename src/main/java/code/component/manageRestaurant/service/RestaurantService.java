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

   }

   public List<Restaurant> getPageByParent(Object parentKey, Integer page) {
      return null;
   }

   public void deleteById(Integer id) {

   }

   public void updateAddress(Restaurant restaurant) {

   }

   public void updateRange(Restaurant restaurant) {

   }

   public List<Restaurant> getPage(Integer page) {
      return null;
   }
}