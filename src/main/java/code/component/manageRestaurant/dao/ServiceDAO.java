package code.component.manageRestaurant.dao;

import code.component.manageRestaurant.domain.Restaurant;

import java.util.List;

public interface ServiceDAO {

   List<Restaurant> getRestaurantsByAddressProximity();



}