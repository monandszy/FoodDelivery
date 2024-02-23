package code.component.manageOrder.web.order;

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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class OrderController {

   public static final String ORDER = "order/";
   private OrderService orderService;
   private OrderDTOMapper orderDTOMapper;

   @GetMapping(ORDER + "getIncompleteBySeller/{sellerId}")
   public String getIncompleteOrdersBySellerId(
       @PathVariable Integer sellerId,
       Model model
   ) {
      List<Order> orderList = orderService.getIncompleteOrdersBySellerId(sellerId);
      List<OrderDTO> orders = orderList.stream().map(orderDTOMapper::mapToDTO).toList();
      model.addAttribute("orders", orders);
      return "seller/order/orders";
   }

   @GetMapping(ORDER + "getForSeller/{orderId}")
   public String getOrderPositionsForSeller(
       @PathVariable Integer orderId,
       @RequestParam(value = "sellerId", required = false) Integer sellerId,
       Model model
   ) {
      Set<OrderPosition> orderList = orderService.getOrderPositions(orderId);
      Set<OrderPositionDTO> orderPositions = orderList.stream().map(orderDTOMapper::mapToDTO).collect(Collectors.toSet());
      model.addAttribute("sellerId", sellerId);
      model.addAttribute("orderPositions", orderPositions);
      return "seller/order/order";
   }

   @DeleteMapping(ORDER + "/complete")
   public String completeOrder(
       @ModelAttribute("orderDTO") OrderDTO orderDTO
//       @RequestParam("sellerId") OrderDTO sellerId
       // might need to refresh after update
   ) {
      orderService.completeOrder(orderDTOMapper.mapFromDTO(orderDTO));
      return "redirect:/order";
   }
}