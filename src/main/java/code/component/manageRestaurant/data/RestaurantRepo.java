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
      return null;
   }

   @Override
   public void deleteById(Integer id) {

   }

   @Override
   public void updateAddress(Restaurant restaurant) {
      throw new RuntimeException("not implemented");
   }

   @Override
   public void updateRange(Restaurant restaurant) {
      throw new RuntimeException("not implemented");
   }

   @Override
   public List<Restaurant> getPage(Integer page) {
      return null;
   }
}