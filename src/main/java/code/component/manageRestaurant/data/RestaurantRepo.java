package code.component.manageRestaurant.data;

import code.component.manageRestaurant.dao.RestaurantDAO;
import code.component.manageRestaurant.domain.Restaurant;

import java.util.List;

public class RestaurantRepo implements RestaurantDAO {
   @Override
   public void add(Restaurant restaurant) {

   }

   @Override
   public List<Restaurant> getPageByParent(Object parentKey, Integer page) {
      return null;
   }

   @Override
   public void deleteById(Integer id) {

   }

   @Override
   public void updateAddress(Restaurant restaurant) {
      throw new RuntimeException("not implemented");
   }

   @Override
   public void updateRange(Restaurant restaurant) {
      throw new RuntimeException("not implemented");
   }
}