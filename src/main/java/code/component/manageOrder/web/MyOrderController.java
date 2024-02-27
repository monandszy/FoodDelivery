package code.component.manageOrder.web;

import code.component.manageAccount.AccountService;
import code.component.manageOrder.OrderService;
import code.component.manageOrder.domain.OrderDTO;
import code.component.manageOrder.domain.OrderPositionDTO;
import code.component.manageOrder.domain.mapper.OrderDTOMapper;
import code.component.manageRestaurant.manageDelivery.domain.AddressDTO;
import code.component.manageRestaurant.manageDelivery.domain.AddressDTOMapper;
import code.configuration.Constants;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
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
       @RequestParam("selectedPositions") Integer[] selected,
       HttpSession session
   ) {
      if (selected.length == 0) throw new RuntimeException(
          "Your can't order nothing, pick an order Position before proceeding");
      AddressDTO address = (AddressDTO) session.getAttribute(Constants.ADDRESS);
      Integer restaurantId = (Integer) session.getAttribute(Constants.RESTAURANT);
      System.out.println(Arrays.toString(selected));
      orderService.addOrder(selected,
          addressDTOMapper.mapFromDTO(address),
          restaurantId
      );
      return "redirect:/order/getByClient";
   }

   @PostMapping(ORDER_DELETE)
   public String deleteOrder(
       @PathVariable("orderId") Integer orderId
   ) {
      orderService.cancelOrder(orderId);
      return "redirect:/order/getByClient";
   }
}