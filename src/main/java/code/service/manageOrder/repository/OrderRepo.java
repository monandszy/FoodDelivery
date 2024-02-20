package code.service.manageOrder.repository;

import code.service.manageOrder.OrderDAO;
import code.service.manageOrder.domain.Order;

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