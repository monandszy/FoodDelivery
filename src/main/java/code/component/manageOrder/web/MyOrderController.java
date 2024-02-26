package code.component.manageOrder.web;

import code.component.manageAccount.AccountService;
import code.component.manageOrder.OrderService;
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
   public static final String ORDER_DELETE = ORDER + "/delete/{orderId}";

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
      List<OrderDTO> orders = orderDTOMapper.
          mapOToDTOList(orderService.getOrdersByClientId(clientId));
      model.addAttribute("myOrders", orders);
      return "client/order/myOrders";
   }

   @GetMapping(ORDER_getForClient)
   public String getOrderPositionsForClient(
       @PathVariable Integer orderId,
       Model model
   ) {
      List<OrderPositionDTO> orderPositions = orderDTOMapper.
          mapOPToDTOList(orderService.getOrderPositions(orderId));
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
              .map(menuPosition -> OrderPosition.builder()
                  .menuPosition(menuPosition).build()).toList(),
          addressDTOMapper.mapFromDTO(address),
          restaurantId
      );
      return "redirect:myOrders/getOrdersByClientId";
   }

   @PostMapping(ORDER_DELETE)
   public String deleteOrder(
       @PathVariable("orderId") Integer orderId
   ) {
      orderService.cancelOrder(orderId);
      return "redirect:myOrders/getOrdersByClientId";
   }
}