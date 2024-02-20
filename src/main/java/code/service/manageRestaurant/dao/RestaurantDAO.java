package code.service.manageRestaurant.dao;

import code.service.manageRestaurant.domain.Restaurant;

public interface RestaurantDAO extends CrudDAO<Restaurant> {

   void updateAddress(Restaurant restaurant);

   void updateRange(Restaurant restaurant);

}