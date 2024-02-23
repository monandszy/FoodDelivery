package code.component.manageOrder.web;

import code.component.manageAccount.UserAccountDetailsService;
import code.component.manageOrder.OrderService;
import code.component.manageOrder.domain.Order;
import code.component.manageOrder.domain.OrderDTO;
import code.component.manageOrder.domain.OrderPosition;
import code.component.manageOrder.domain.OrderPositionDTO;
import code.component.manageOrder.domain.mapper.OrderDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor
public class OrderController {

   public static final String ORDER = "order";
   private OrderService orderService;
   private OrderDTOMapper orderDTOMapper;
   private UserAccountDetailsService accountService;

   @GetMapping(ORDER + "/getIncompleteBySeller")
   public String getIncompleteOrdersBySellerId(
       Model model
   ) {
      String sellerId = accountService.getAuthenticatedUserName();
      List<Order> orderList = orderService.getIncompleteOrdersBySellerId(sellerId);
      List<OrderDTO> orders = orderList.stream().map(orderDTOMapper::mapToDTO).toList();
      model.addAttribute("orders", orders);
      return "seller/order/orders";
   }

   @GetMapping(ORDER + "/getForSeller/{orderId}")
   public String getOrderPositionsForSeller(
       @PathVariable Integer orderId,
       Model model
   ) {
      List<OrderPosition> orderList = orderService.getOrderPositions(orderId);
      List<OrderPositionDTO> orderPositions = orderList.stream().map(orderDTOMapper::mapToDTO).toList();
      model.addAttribute("orderPositions", orderPositions);
      return "seller/order/order";
   }

   @DeleteMapping(ORDER + "/complete")
   public String completeOrder(
       @ModelAttribute("orderDTO") OrderDTO orderDTO
   ) {
      orderService.completeOrder(orderDTOMapper.mapFromDTO(orderDTO));
      return "redirect:/order/getIncompleteBySeller";
   }
}