package code.service.manageRestaurant.dao;

import code.service.manageRestaurant.domain.Restaurant;

import java.util.List;

public interface ManagementDAO {

   List<Restaurant> getRestaurantsByAddressProximity();



}