package code.component.manageOrder.repository;

import code.component.manageOrder.OrderDAO;
import code.component.manageOrder.domain.Order;

import java.util.Set;

public class OrderRepo implements OrderDAO {

   OrderJpaRepo orderJpaRepo;

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