package code.component.manageOrder.web;

import code.component.manageAccount.AccountService;
import code.component.manageOrder.OrderService;
import code.component.manageOrder.domain.OrderDTO;
import code.component.manageOrder.domain.OrderPositionDTO;
import code.component.manageOrder.domain.mapper.OrderDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor
public class OrderController {

   public static final String ORDER = "order";
   public static final String ORDER_getBySeller = ORDER + "/getIncompleteBySeller";
   public static final String ORDER_getForSeller = ORDER + "/getForSeller/{orderId}";
   public static final String ORDER_COMPLETE = ORDER + "/complete/{orderId}";

   private OrderService orderService;
   private OrderDTOMapper dtoMapper;
   private AccountService accountService;

   @GetMapping(ORDER_getBySeller)
   public String getIncompleteOrdersBySellerId(
       Model model
   ) {
      String sellerId = accountService.getAuthenticatedUserName();
      List<OrderDTO> orders = dtoMapper.mapOToDTOList(
          orderService.getIncompleteOrdersBySellerId(sellerId));
      model.addAttribute("orders", orders);
      return "seller/order/orders";
   }

   @GetMapping(ORDER_getForSeller)
   public String getOrderPositionsForSeller(
       @PathVariable String orderId,
       Model model
   ) {
      List<OrderPositionDTO> orderPositions = dtoMapper.mapOPToDTOList(
          orderService.getOrderPositions(Integer.valueOf(orderId)));
      model.addAttribute("orderPositions", orderPositions);
      return "seller/order/order";
   }

   @PatchMapping(ORDER_COMPLETE)
   public String completeOrder(
       @PathVariable("orderId") String orderId
   ) {
      orderService.complete(Integer.valueOf(orderId));
      return "redirect:/order/getIncompleteBySeller";
   }
}