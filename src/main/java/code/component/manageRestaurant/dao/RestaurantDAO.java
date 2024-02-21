package code.component.manageRestaurant.dao;

import code.component.manageRestaurant.domain.Restaurant;

import java.util.List;


public interface RestaurantDAO extends CrudDAO<Restaurant> {

   void updateAddress(Restaurant restaurant);

   void updateRange(Restaurant restaurant);

   List<Restaurant> getPage(Integer page);

}