package code.orderManagement.data;

import code.component.manageAccount.domain.mapper.AccountEntityMapperImpl;
import code.component.manageOrder.data.OrderRepo;
import code.component.manageOrder.domain.Order;
import code.component.manageOrder.domain.OrderPosition;
import code.component.manageOrder.domain.mapper.OrderEntityMapperImpl;
import code.component.manageRestaurant.data.MenuPositionRepo;
import code.component.manageRestaurant.data.MenuRepo;
import code.component.manageRestaurant.data.RestaurantRepo;
import code.component.manageRestaurant.domain.mapper.RestaurantEntityMapperImpl;
import code.component.manageRestaurant.manageDelivery.AddressRepo;
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
    OrderRepo.class,
    RestaurantRepo.class,
    AddressRepo.class,
    MenuRepo.class,
    MenuPositionRepo.class,
    OrderEntityMapperImpl.class,
    AddressEntityMapperImpl.class,
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
      Order order = testAddOrder(1, sellerId, clientId);
      testAddOrderPosition(1, order);

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

   private Order testAddOrder(int id, String sellerId, String clientId) {
      restaurantRepo.add(DataFixtures.getRestaurant().withId(id), sellerId);
      addressRepo.add(DataFixtures.getAddress().withId(id));

      return orderRepo.add(DataFixtures.getOrder(),
          id, sellerId, clientId, id
      );
   }

   private void testAddOrderPosition(int id, Order order) {
      menuRepo.add(DataFixtures.getMenu().withId(id), id);
      menuPositionRepo.add(DataFixtures.getMenuPosition().withId(id), id);
      orderRepo.addOrderPositions(List.of(DataFixtures.getOrderPosition()),
          List.of(id), order.getId());
   }


   @Test
   @Transactional
   void testUpdate() {
      String userName = "admin";
      int order1Id = 1;
      Order order = testAddOrder(order1Id, userName, userName);
      testAddOrderPosition(order1Id, order);
      orderRepo.delete(order.getId());
      Assertions.assertThrows(Exception.class, () -> orderRepo.getById(order.getId()));
      Order order2 = testAddOrder(2, userName, userName);
      orderRepo.updateOrderStatus(order2.getId(), Order.OrderStatus.COMPLETED);
      Order orderById = orderRepo.getById(order2.getId());
      Assertions.assertEquals(Order.OrderStatus.COMPLETED, orderById.getStatus());
   }

}