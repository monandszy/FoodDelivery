package code.component.manageOrder;

import code.component.manageOrder.domain.Order;
import code.component.manageOrder.domain.OrderPosition;

import java.util.List;

public interface OrderDAO {


   List<OrderPosition> getOrderPositions(Integer orderId);

   void addOrder(Order build);

   void cancelOrder(Integer orderId);

   void completeOrder(Order order);

   List<Order> getIncompleteOrdersBySellerId(String sellerId);

   List<Order> getOrdersByClientId(String clientId);

   Order getOrderById(Integer orderId);
}