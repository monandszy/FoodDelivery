package code.component.manageOrder;

import code.component.manageAccount.AccountService;
import code.component.manageOrder.domain.Order;
import code.component.manageOrder.domain.OrderPosition;
import code.component.manageRestaurant.manageDelivery.domain.Address;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Arrays;
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
   public void addOrder(Integer[] selected, Address address, Integer restaurantId) {
      orderDAO.addOrder(Order.builder()
          .address(address)
          .restaurantId(restaurantId)
          .client(accountService.getAuthenticatedAccount())
          .status(Order.OrderStatus.IN_PROGRESS)
          .timeOfOrder(OffsetDateTime.now())
          .orderPositions(Arrays.stream(selected).map(e ->
              OrderPosition.builder().menuPositionId(e).build()).toList()).build());
   }

   @Transactional
   public void cancelOrder(Integer orderId) {
      Order order = orderDAO.getOrderById(orderId);
      if (order.getStatus() != Order.OrderStatus.IN_PROGRESS)
         throw new RuntimeException("Order status not IN_PROGRESS, cannot cancel");
      if (order.getTimeOfOrder().isBefore(OffsetDateTime.now().minusMinutes(20)))
         throw new RuntimeException("20 minute return timer has already expired");
      orderDAO.cancelOrder(orderId);
   }

   @Transactional
   public void complete(Integer orderId) {
      Order order = orderDAO.getOrderById(orderId);
      accountService.getAuthenticatedUserName();
      // TODO verify seller session - restaurantId instead of SellerId in Order might be a bad idea?
      // I need restaurantId for address comparing?
      // add RestaurantEntity - from session and SellerEntity - from DTO?
      orderDAO.completeOrder(order.withStatus(Order.OrderStatus.COMPLETED));
   }

   public List<Order> getIncompleteOrdersBySellerId(String sellerId) {
      return orderDAO.getIncompleteOrdersBySellerId(sellerId);
   }

   public List<Order> getOrdersByClientId(String clientId) {
      return orderDAO.getOrdersByClientId(clientId);
   }
}