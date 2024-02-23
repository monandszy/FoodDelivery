package code.component.manageOrder.web.order;

import code.component.manageAccount.UserAccountDetailsService;
import code.component.manageOrder.OrderService;
import code.component.manageOrder.domain.Order;
import code.component.manageOrder.domain.OrderDTO;
import code.component.manageOrder.domain.OrderPosition;
import code.component.manageOrder.domain.OrderPositionDTO;
import code.component.manageOrder.domain.mapper.OrderDTOMapper;
import code.component.manageRestaurant.domain.MenuPositionDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class MyOrderController {

   public static final String ORDER = "order";
   private OrderService orderService;
   private OrderDTOMapper orderDTOMapper;
   private UserAccountDetailsService accountService;

   @GetMapping(ORDER + "/getByClient")
   public String getOrdersByClientId(
       Model model
   ) {
      String clientId = accountService.getAuthenticatedUserName();
      List<Order> orderList = orderService.getIncompleteOrdersBySellerId(clientId);
      List<OrderDTO> orders = orderList.stream().map(orderDTOMapper::mapToDTO).toList();
      model.addAttribute("myOrders", orders);
      return "client/order/myOrders";
   }

   @GetMapping(ORDER + "/getForClient/{orderId}")
   public String getOrderPositionsForClient(
       @PathVariable Integer orderId,
       Model model
   ) {
      Set<OrderPosition> orderList = orderService.getOrderPositions(orderId);
      Set<OrderPositionDTO> orderPositions = orderList.stream().map(orderDTOMapper::mapToDTO).collect(Collectors.toSet());
      model.addAttribute("orderPositions", orderPositions);
      return "client/order/myOrder";
   }

   @PostMapping(ORDER + "/add")
   public String postOrder(
       @ModelAttribute("orderList") List<MenuPositionDTO> menuPositions
       // might need client id here too
   ) {
      orderService.createOrder(menuPositions);
      return "redirect:myOrders/getOrdersByClientId";
   }

   @DeleteMapping(ORDER + "/delete")
   public String deleteOrder(
       @ModelAttribute("orderDTO") OrderDTO orderDTO
   ) {
      orderService.deleteOrder(orderDTOMapper.mapFromDTO(orderDTO));
      return "redirect:myOrders/getOrdersByClientId";
   }
}