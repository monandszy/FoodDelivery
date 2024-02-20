package code.component.manageRestaurant.dao;

import code.component.manageRestaurant.domain.Restaurant;

import java.util.List;

public interface ManagementDAO {

   List<Restaurant> getRestaurantsByAddressProximity();



}