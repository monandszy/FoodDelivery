package code.component.manageOrder.web.order;

import code.component.manageOrder.OrderService;
import code.component.manageOrder.domain.OrderDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class OrderController {

   public static final String ORDER = "order";
   private OrderService orderService;

   @GetMapping(ORDER + "getIncompleteBySeller/{sellerId}")
   public String getIncompleteOrdersBySeller(
       @PathVariable String sellerId
   ) {
      return null;
   }

   @GetMapping(ORDER + "getByClient/{clientId}")
   public String getOrderByClient(
       @PathVariable String clientId
   ) {
      return null;
   }

   @PostMapping(ORDER + "/add")
   public String postOrder(
       @RequestParam("orderList")List<OrderDTO> orderList
       ) {
      return null;
   }

   @DeleteMapping(ORDER + "/delete/{orderId}")
   public String deleteOrder(
       @PathVariable String orderId
   ) {
      return null;
   }

   @DeleteMapping(ORDER + "/complete/{orderId}")
   public String completeOrder(
       @PathVariable String orderId
   ) {
      return null;
   }

}