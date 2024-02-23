package code.component.manageOrder;

import code.component.manageOrder.domain.Order;
import code.component.manageOrder.domain.OrderPosition;
import code.component.manageRestaurant.domain.MenuPositionDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class OrderService {

   private OrderDAO orderDAO;

   public Set<OrderPosition> getOrderPositions(Integer orderId) {
      return Set.of();
   }

   public void createOrder(List<MenuPositionDTO> order) {

   }

   public void deleteOrder(Order order) {

   }

   public void completeOrder(Order order) {

   }

   public List<Order> getIncompleteOrdersBySellerId(Integer sellerId) {

      return List.of();
   }

}