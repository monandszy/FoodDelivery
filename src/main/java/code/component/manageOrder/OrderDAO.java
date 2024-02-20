package code.component.manageOrder;

import code.component.manageOrder.domain.Order;

import java.util.Set;

public interface OrderDAO {

   Set<Order> getOrders();

   void addOrder(Order order);

   void deleteOrder(Order order);

   void updateOrder(Order order);
}