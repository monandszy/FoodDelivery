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
   public List<OrderPosition> getOrderPositionsByOrderId(Integer orderId) {
      return null;
   }

   @Override
   public void addOrder(Order build) {

   }

   @Override
   public void cancelOrder() {

   }

   @Override
   public void updateOrder(Order order) {

   }

   @Override
   public List<Order> getIncompleteOrdersBySellerId(String sellerId) {
      return null;
   }

   @Override
   public List<Order> getOrdersByClientId(String clientId) {
      return null;
   }
}