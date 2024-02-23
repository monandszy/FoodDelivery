package code.component.manageOrder;

import code.component.manageOrder.domain.Order;
import code.component.manageOrder.domain.OrderPosition;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

   private OrderDAO orderDAO;

   @Transactional
   public List<OrderPosition> getOrderPositions(Integer orderId) {
      return orderDAO.getOrderPositionsByOrderId(orderId);
   }

   @Transactional
   public void addOrder(List<OrderPosition> order) {
      orderDAO.addOrder(Order.builder()
          .status(Order.OrderStatus.IN_PROGRESS)
          .timeOfOrder(OffsetDateTime.now())
          .menuPositions(order).build());
   }

   @Transactional
   public void cancelOrder(Order order) {
      int i = order.getTimeOfOrder().compareTo(OffsetDateTime.now().minusMinutes(20));
      if (i >= 0) {
         orderDAO.cancelOrder();
      } else {
         throw new RuntimeException("Sorry you can't cancel your order anymore");
      }
   }

   @Transactional
   public void completeOrder(Order order) {
      orderDAO.updateOrder(order.withStatus(Order.OrderStatus.COMPLETED));
   }
   public List<Order> getIncompleteOrdersBySellerId(String sellerId) {
      return orderDAO.getIncompleteOrdersBySellerId(sellerId);
   }

   public List<Order> getOrdersByClientId(String clientId) {
      return orderDAO.getOrdersByClientId(clientId);
   }
}