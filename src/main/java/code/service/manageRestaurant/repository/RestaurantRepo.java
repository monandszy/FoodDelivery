package code.service.manageRestaurant.repository;

import code.service.manageRestaurant.dao.RestaurantDAO;
import code.service.manageRestaurant.domain.Restaurant;

import java.util.List;

public class RestaurantRepo implements RestaurantDAO {
   @Override
   public void add(Restaurant restaurant) {

   }

   @Override
   public List<Restaurant> getPageByParent(Object parentKey) {
      return null;
   }

   @Override
   public void deleteById(Integer id) {

   }

   @Override
   public void updateAddress(Restaurant restaurant) {

   }

   @Override
   public void updateRange(Restaurant restaurant) {

   }
}