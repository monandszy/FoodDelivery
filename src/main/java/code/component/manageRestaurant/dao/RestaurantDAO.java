package code.component.manageRestaurant.dao;

import code.component.manageRestaurant.domain.Restaurant;

import java.util.List;


public interface RestaurantDAO extends CrudDAO<Restaurant> {

   List<Restaurant> getPageBySeller(String sellerId, Integer page);

   Restaurant add(Restaurant restaurant, String sellerId);

   Restaurant getByRestaurantId(Integer restaurantId);
}