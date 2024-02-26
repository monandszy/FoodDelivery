package code.component.manageOrder.data;

import code.component.manageOrder.OrderDAO;
import code.component.manageOrder.domain.Order;
import code.component.manageOrder.domain.OrderPosition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepo implements OrderDAO {

   OrderJpaRepo orderJpaRepo;
   OrderPositionJpaRepo orderPositionJpaRepo;

   @Override
   public List<OrderPosition> getOrderPositions(Integer orderId) {
      return List.of();
   }

   @Override
   public void addOrder(Order build) {
      // restaurantId will be added;
   }

   @Override
   public void cancelOrder(Integer orderId) {

   }

   @Override
   public void updateOrder(Order order) {

   }

   @Override
   public List<Order> getIncompleteOrdersBySellerId(String sellerId) {
      return List.of();
   }

   @Override
   public List<Order> getOrdersByClientId(String clientId) {
      return List.of();
   }

   @Override
   public Order getOrderById(Integer orderId) {
      return null;
   }
}