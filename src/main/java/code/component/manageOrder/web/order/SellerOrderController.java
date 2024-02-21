package code.component.manageOrder.web.order;

import code.component.manageOrder.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class SellerOrderController {

   public static final String ORDER = "order";
   private OrderService orderService;

   @GetMapping("/{orderId}")
   void getOrder(
       @PathVariable String orderId
   ) {
//      orderService.getOrdersByClientCode();
//      orderService.getOrdersBySellerCode();
   }

   void postOrder() {

   }

   void deleteOrder() {

   }

}