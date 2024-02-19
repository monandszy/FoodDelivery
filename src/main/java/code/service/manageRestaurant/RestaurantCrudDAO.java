package code.service.manageRestaurant;

import code.service.manageRestaurant.domain.Menu;
import code.service.manageRestaurant.domain.MenuPosition;
import code.service.manageRestaurant.domain.Restaurant;

import java.util.Set;

public interface RestaurantCrudDAO {

   void addRestaurant(Restaurant restaurant);

   void addMenu(Menu menu);

   void addPosition(MenuPosition menuPosition);

   Set<Restaurant> getRestaurants();

   Set<Menu> getMenus();

   Set<MenuPosition> getPositions();

   void deleteRestaurant(Restaurant restaurant);

   void deleteMenu(Menu menu);

   void deleteMenuPosition(MenuPosition menuPosition);

}