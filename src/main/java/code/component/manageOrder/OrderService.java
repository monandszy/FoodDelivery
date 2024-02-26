package code.component.manageOrder;

import code.component.manageAccount.AccountService;
import code.component.manageOrder.domain.Order;
import code.component.manageOrder.domain.OrderPosition;
import code.component.manageRestaurant.manageDelivery.domain.Address;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

   private OrderDAO orderDAO;
   private AccountService accountService;

   @Transactional
   public List<OrderPosition> getOrderPositions(Integer orderId) {
      return orderDAO.getOrderPositions(orderId);
   }

   @Transactional
   public void addOrder(List<OrderPosition> order, Address address, Integer restaurantId) {
      if (order.isEmpty()) throw new RuntimeException(
          "Your can't order nothing, pick an order Position before proceeding");
      orderDAO.addOrder(Order.builder()
          .address(address)
          .restaurantId(restaurantId)
          .client(accountService.getAuthenticatedAccount())
          .status(Order.OrderStatus.IN_PROGRESS)
          .timeOfOrder(OffsetDateTime.now())
          .orderPositions(order).build());
   }

   @Transactional
   public void cancelOrder(Integer orderId) {
      Order order = orderDAO.getOrderById(orderId);
      // check for status! - can't cancel delivered
      int i = order.getTimeOfOrder().compareTo(OffsetDateTime.now().minusMinutes(20));
      if (i >= 0) {
         orderDAO.cancelOrder(orderId);
      } else {
         throw new RuntimeException("Sorry you can't cancel your order anymore");
      }
   }

   @Transactional
   public void complete(Integer orderId) {
      Order order = orderDAO.getOrderById(orderId);
      // verify seller session
      // update data
      orderDAO.updateOrder(order);
   }

   public List<Order> getIncompleteOrdersBySellerId(String sellerId) {
      return orderDAO.getIncompleteOrdersBySellerId(sellerId);
   }

   public List<Order> getOrdersByClientId(String clientId) {
      return orderDAO.getOrdersByClientId(clientId);
   }
}