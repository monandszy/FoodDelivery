package code.component.manageRestaurant.data;

import code.component.manageRestaurant.dao.RestaurantDAO;
import code.component.manageRestaurant.domain.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class RestaurantRepo implements RestaurantDAO {
   @Override
   public void add(Restaurant restaurant) {

   }

   @Override
   public List<Restaurant> getPageByParent(Object parentKey, Integer page) {
      // get page by sellerId
      return List.of();
   }

   @Override
   public void deleteById(Integer id) {

   }
}