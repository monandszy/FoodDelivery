package code.component.manageOrder.web;

import code.component.manageAccount.AccountService;
import code.component.manageOrder.OrderService;
import code.component.manageOrder.domain.Order;
import code.component.manageOrder.domain.OrderDTO;
import code.component.manageOrder.domain.OrderPosition;
import code.component.manageOrder.domain.OrderPositionDTO;
import code.component.manageOrder.domain.mapper.OrderDTOMapper;
import code.component.manageRestaurant.domain.MenuPositionDTO;
import code.component.manageRestaurant.domain.mapper.RestaurantDTOMapper;
import code.component.manageRestaurant.manageDelivery.domain.AddressDTO;
import code.component.manageRestaurant.manageDelivery.domain.AddressDTOMapper;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class MyOrderController {

   public static final String ORDER = "order";
   public static final String ORDER_getByClient = ORDER + "/getByClient";
   public static final String ORDER_getForClient = ORDER + "/getForClient/{orderId}";
   public static final String ORDER_ADD = ORDER + "/add";
   public static final String ORDER_DELETE = ORDER + "/delete";

   private OrderService orderService;
   private OrderDTOMapper orderDTOMapper;
   private RestaurantDTOMapper restaurantDTOMapper;
   private AddressDTOMapper addressDTOMapper;
   private AccountService accountService;

   @GetMapping(ORDER_getByClient)
   public String getOrdersByClientId(
       Model model
   ) {
      String clientId = accountService.getAuthenticatedUserName();
      List<Order> orderList = orderService.getOrdersByClientId(clientId);
      List<OrderDTO> orders = orderList.stream().map(orderDTOMapper::mapToDTO).toList();
      model.addAttribute("myOrders", orders);
      return "client/order/myOrders";
   }

   @GetMapping(ORDER_getForClient)
   public String getOrderPositionsForClient(
       @PathVariable Integer orderId,
       Model model
   ) {
      List<OrderPosition> orderList = orderService.getOrderPositions(orderId);
      List<OrderPositionDTO> orderPositions = orderList.stream().map(orderDTOMapper::mapToDTO).toList();
      model.addAttribute("orderPositions", orderPositions);
      return "client/order/myOrder";
   }

   @PostMapping(ORDER_ADD)
   public String postOrder(

       @ModelAttribute("orderList") List<MenuPositionDTO> menuPositions,
       HttpSession session
   ) {
      AddressDTO address = (AddressDTO) session.getAttribute("ADDRESS");
      Integer restaurantId = (Integer) session.getAttribute("RESTAURANT");
      orderService.addOrder(menuPositions.stream()
              .map(restaurantDTOMapper::mapFromDTO)
              .map(e -> OrderPosition.builder().menuPosition(e).build()).toList(),
          addressDTOMapper.mapFromDTO(address),
          restaurantId
      );
      return "redirect:myOrders/getOrdersByClientId";
   }

   @DeleteMapping(ORDER_DELETE)
   public String deleteOrder(
       @ModelAttribute("orderDTO") OrderDTO orderDTO
   ) {
      orderService.cancelOrder(orderDTOMapper.mapFromDTO(orderDTO));
      return "redirect:myOrders/getOrdersByClientId";
   }
}