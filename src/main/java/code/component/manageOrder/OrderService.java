package code.component.manageOrder;

import code.component.manageOrder.domain.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class OrderService {

   private OrderDAO orderDAO;

   public Set<Order> getOrderPositions() {
      return null;
   }

   public void addOrder(Order order) {

   }

   public void deleteOrder(Order order) {

   }

   public void completeOrder(Order order) {

   }
}