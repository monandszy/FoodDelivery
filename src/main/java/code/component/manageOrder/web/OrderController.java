package code.component.manageOrder.web;

import code.component.manageAccount.AccountService;
import code.component.manageOrder.OrderService;
import code.component.manageOrder.domain.Order;
import code.component.manageOrder.domain.OrderDTO;
import code.component.manageOrder.domain.OrderPosition;
import code.component.manageOrder.domain.OrderPositionDTO;
import code.component.manageOrder.domain.mapper.OrderDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor
public class OrderController {

   public static final String ORDER = "order";
   public static final String ORDER_getBySeller = ORDER + "/getIncompleteBySeller";
   public static final String ORDER_getForSeller = ORDER + "/getForSeller/{orderId}";
   public static final String ORDER_COMPLETE = ORDER + "/complete";
   private OrderService orderService;
   private OrderDTOMapper orderDTOMapper;
   private AccountService accountService;

   @GetMapping(ORDER_getBySeller)
   public String getIncompleteOrdersBySellerId(
       Model model
   ) {
      String sellerId = accountService.getAuthenticatedUserName();
      List<Order> orderList = orderService.getIncompleteOrdersBySellerId(sellerId);
      List<OrderDTO> orders = orderList.stream().map(orderDTOMapper::mapToDTO).toList();
      model.addAttribute("orders", orders);
      return "seller/order/orders";
   }

   @GetMapping(ORDER_getForSeller)
   public String getOrderPositionsForSeller(
       @PathVariable Integer orderId,
       Model model
   ) {
      List<OrderPosition> orderList = orderService.getOrderPositions(orderId);
      List<OrderPositionDTO> orderPositions = orderList.stream().map(orderDTOMapper::mapToDTO).toList();
      model.addAttribute("orderPositions", orderPositions);
      return "seller/order/order";
   }

   @PatchMapping(ORDER_COMPLETE)
   public String completeOrder(
       @ModelAttribute("orderDTO") OrderDTO orderDTO
   ) {
      orderService.completeOrder(orderDTOMapper.mapFromDTO(orderDTO));
      return "redirect:/order/getIncompleteBySeller";
   }
}