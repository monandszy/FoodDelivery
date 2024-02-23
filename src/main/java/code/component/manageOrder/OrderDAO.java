package code.component.manageOrder;

import code.component.manageOrder.domain.Order;
import code.component.manageOrder.domain.OrderPosition;

import java.util.List;

public interface OrderDAO {


   List<OrderPosition> getOrderPositions(Integer orderId);

   void addOrder(Order build);

   void cancelOrder();

   void updateOrder(Order order);

   List<Order> getIncompleteOrdersBySellerId(String sellerId);

   List<Order> getOrdersByClientId(String clientId);
}