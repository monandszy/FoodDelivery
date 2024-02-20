package code.component.manageRestaurant.dao;

import code.component.manageRestaurant.domain.Restaurant;

public interface RestaurantDAO extends CrudDAO<Restaurant> {

   void updateAddress(Restaurant restaurant);

   void updateRange(Restaurant restaurant);

}