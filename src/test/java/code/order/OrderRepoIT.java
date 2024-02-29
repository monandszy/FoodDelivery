package code.order;

import code.component.manageAccount.domain.mapper.AccountEntityMapperImpl;
import code.component.manageOrder.data.OrderRepo;
import code.component.manageOrder.domain.Order;
import code.component.manageOrder.domain.OrderPosition;
import code.component.manageOrder.domain.mapper.OrderEntityMapperImpl;
import code.component.manageRestaurant.data.MenuPositionRepo;
import code.component.manageRestaurant.data.MenuRepo;
import code.component.manageRestaurant.data.RestaurantRepo;
import code.component.manageRestaurant.domain.Menu;
import code.component.manageRestaurant.domain.MenuPosition;
import code.component.manageRestaurant.domain.Restaurant;
import code.component.manageRestaurant.domain.mapper.RestaurantEntityMapperImpl;
import code.component.manageRestaurant.manageDelivery.AddressRepo;
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
    OrderRepo.class,
    RestaurantRepo.class,
    AddressRepo.class,
    MenuRepo.class,
    MenuPositionRepo.class,
    OrderEntityMapperImpl.class,
//    AddressEntityMapperImpl.class,
    RestaurantEntityMapperImpl.class,
    AccountEntityMapperImpl.class,
})
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderRepoIT extends AbstractJpaIT {

   private OrderRepo orderRepo;
   private RestaurantRepo restaurantRepo;
   private MenuRepo menuRepo;
   private MenuPositionRepo menuPositionRepo;
   private AddressRepo addressRepo;

   @Test
   @Transactional
   void testGet() {
      String sellerId = "admin";
      String clientId = "admin";
      Order order = testAddOrder(sellerId, clientId);
      testAddOrderPosition(order.getRestaurant().getId(), order);

      List<Order> ordersByClientId = orderRepo.getOrdersByClientId(clientId);
      List<Order> incompleteOrdersBySellerId = orderRepo.getIncompleteOrdersBySellerId(sellerId);
      Assertions.assertFalse(ordersByClientId.isEmpty());
      Assertions.assertFalse(incompleteOrdersBySellerId.isEmpty());

      Integer orderId = order.getId();
      Order orderById = orderRepo.getById(orderId);
      Assertions.assertEquals(order, orderById);

      List<OrderPosition> orderPositions = orderRepo.getOrderPositions(orderId);
      Assertions.assertFalse(orderPositions.isEmpty());
   }

   private Order testAddOrder(String sellerId, String clientId) {
      Restaurant add = restaurantRepo.add(DataFixtures.getRestaurant(), sellerId);
      addressRepo.add(DataFixtures.getAddress());

      return orderRepo.add(DataFixtures.getOrder(),
          null, sellerId, clientId, add.getId()
      ).withRestaurant(add);
   }

   private void testAddOrderPosition(int restaurantId, Order order) {
      Menu add = menuRepo.add(DataFixtures.getMenu(), restaurantId);
      MenuPosition add1 = menuPositionRepo.add(DataFixtures.getMenuPosition(), add.getId());
      orderRepo.addOrderPositions(List.of(DataFixtures.getOrderPosition()),
          List.of(add1.getId()), order.getId());
   }


   @Test
   @Transactional
   void testUpdate() {
      String userName = "admin";
      Order order = testAddOrder(userName, userName);
      testAddOrderPosition(order.getRestaurant().getId(), order);
      orderRepo.delete(order.getId());
      Assertions.assertThrows(Exception.class, () -> orderRepo.getById(order.getId()));
      Order order2 = testAddOrder(userName, userName);
      orderRepo.updateOrderStatus(order2.getId(), Order.OrderStatus.COMPLETED);
      Order orderById = orderRepo.getById(order2.getId());
      Assertions.assertEquals(Order.OrderStatus.COMPLETED, orderById.getStatus());
   }

}