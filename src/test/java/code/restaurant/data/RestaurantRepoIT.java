package code.restaurant.data;

import code.component.manageAccount.domain.mapper.AccountEntityMapperImpl;
import code.component.manageRestaurant.data.MenuPositionRepo;
import code.component.manageRestaurant.data.MenuRepo;
import code.component.manageRestaurant.data.RestaurantRepo;
import code.component.manageRestaurant.domain.Menu;
import code.component.manageRestaurant.domain.MenuPosition;
import code.component.manageRestaurant.domain.Restaurant;
import code.component.manageRestaurant.domain.mapper.RestaurantEntityMapperImpl;
import code.component.manageRestaurant.manageDelivery.AddressRepo;
import code.component.manageRestaurant.manageDelivery.domain.Address;
import code.component.manageRestaurant.manageDelivery.domain.AddressEntityMapperImpl;
import code.configuration.AbstractJpaIT;
import code.util.DataFixtures;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Import(value = {
    RestaurantRepo.class,
    AddressRepo.class,
    MenuRepo.class,
    MenuPositionRepo.class,
    RestaurantEntityMapperImpl.class,
    AccountEntityMapperImpl.class,
    AddressEntityMapperImpl.class
})
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RestaurantRepoIT extends AbstractJpaIT {

   private RestaurantRepo restaurantRepo;
   private AddressRepo addressRepo;
   private MenuRepo menuRepo;
   private MenuPositionRepo menuPositionRepo;

   @Test
   @Transactional
   void thatCrudWorksCorrectly() {

      Address address = DataFixtures.getAddress();
      addressRepo.add(address);

      String sellerId = "admin";
      restaurantRepo.add(DataFixtures.getRestaurant(), address.getId(), sellerId);
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