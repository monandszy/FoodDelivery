package code.component.manageOrder.web.order;

import code.component.manageOrder.OrderService;
import code.component.manageOrder.domain.OrderDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class OrderController {

   public static final String ORDER = "order";
   private OrderService orderService;

   @GetMapping(ORDER + "getIncompleteBySeller/{sellerId}")
   public String getIncompleteOrdersBySellerId(
       @PathVariable String sellerId
   ) {
      return null;
   }

   @GetMapping(ORDER + "getByClient/{clientId}")
   public String getOrderByClientId(
       @PathVariable String clientId
   ) {
      return null;
   }

   @PostMapping(ORDER + "/add")
   public String postOrder(
       @ModelAttribute("orderList") List<OrderDTO> orderList
       ) {
      return null;
   }

   @DeleteMapping(ORDER + "/delete")
   public String deleteOrder(
       @ModelAttribute("orderDTO") OrderDTO orderDTO
   ) {
      // check for the 20 minute mark!
      return null;
   }

   @DeleteMapping(ORDER + "/complete")
   public String completeOrder(
       @ModelAttribute("orderDTO") OrderDTO orderDTO
   ) {
      return null;
   }
}