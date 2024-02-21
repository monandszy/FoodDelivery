package code.component.manageOrder.data;

import code.component.manageOrder.OrderDAO;
import code.component.manageOrder.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class OrderRepo implements OrderDAO {

   OrderJpaRepo orderJpaRepo;
   OrderPositionJpaRepo orderPositionJpaRepo;

   @Override
   public Set<Order> getOrders() {
      return null;
   }

   @Override
   public void addOrder(Order order) {

   }

   @Override
   public void deleteOrder(Order order) {

   }

   @Override
   public void updateOrder(Order order) {

   }
}