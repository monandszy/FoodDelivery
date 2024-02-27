package code.restaurantManagement.data;

import code.component.manageRestaurant.data.MenuPositionRepo;
import code.component.manageRestaurant.data.MenuRepo;
import code.component.manageRestaurant.data.RestaurantRepo;
import code.component.manageRestaurant.domain.Menu;
import code.component.manageRestaurant.domain.MenuPosition;
import code.component.manageRestaurant.domain.Restaurant;
import code.component.manageRestaurant.domain.mapper.RestaurantEntityMapperImpl;
import code.configuration.AbstractJpaIT;
import code.util.DataFixtures;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(value = {
    RestaurantRepo.class,
    MenuRepo.class,
    MenuPositionRepo.class,
    RestaurantEntityMapperImpl.class
})
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RestaurantRepoTest extends AbstractJpaIT {

   private RestaurantRepo restaurantRepo;
   private MenuRepo menuRepo;
   private MenuPositionRepo menuPositionRepo;

   @Test
   void thatCrudWorksCorrectly() {
      String sellerId = "admin";
      restaurantRepo.add(DataFixtures.getRestaurant(), sellerId);
      List<Restaurant> pageBySeller = restaurantRepo.getPageBySeller(sellerId, 0);
      Assertions.assertFalse(pageBySeller.isEmpty());
      Integer restaurantId = pageBySeller.getFirst().getId();

      menuRepo.add(DataFixtures.getMenu(), restaurantId);
      List<Menu> pageByRestaurantId = menuRepo.getPageByRestaurantId(restaurantId, 0);
      Assertions.assertFalse(pageByRestaurantId.isEmpty());
      Integer menuId = pageByRestaurantId.getFirst().getId();

      menuPositionRepo.add(DataFixtures.getMenuPosition(), menuId);
      List<MenuPosition> menuPositions = menuPositionRepo.getMenuPositions(menuId);
      List<MenuPosition> pageByMenuId = menuPositionRepo.getPageByMenuId(menuId, 0);
      Assertions.assertFalse(menuPositions.isEmpty());
      Assertions.assertFalse(pageByMenuId.isEmpty());
      Integer menuPositionId = pageByMenuId.getFirst().getId();

      menuPositionRepo.deleteById(menuPositionId);
      menuPositions = menuPositionRepo.getMenuPositions(menuId);
      pageByMenuId = menuPositionRepo.getPageByMenuId(menuId, 0);
      Assertions.assertTrue(menuPositions.isEmpty());
      Assertions.assertTrue(pageByMenuId.isEmpty());
      menuRepo.deleteById(menuId);
      pageByRestaurantId = menuRepo.getPageByRestaurantId(restaurantId, 0);
      Assertions.assertTrue(pageByRestaurantId.isEmpty());
      restaurantRepo.deleteById(restaurantId);
      pageBySeller = restaurantRepo.getPageBySeller(sellerId, 0);
      Assertions.assertTrue(pageBySeller.isEmpty());
   }

}