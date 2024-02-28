package code.api;

import code.component.manageOrder.OrderService;
import code.component.manageOrder.domain.Order;
import code.component.manageOrder.domain.OrderPosition;
import code.component.manageOrder.domain.mapper.OrderDTOMapper;
import code.component.manageRestaurant.manageDelivery.domain.AddressDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
public class OrderRestController {

   public static final String API_ORDER = "api/order";

   public static final String ORDER_GET = API_ORDER + "/get/{clientId}";
   public static final String ORDER_GET_DETAILS = API_ORDER + "/details/get/{orderId}";
   public static final String ORDER_ADD = API_ORDER + "/add";
   public static final String ORDER_DELETE = API_ORDER + "/delete/{orderId}";

   private OrderService orderService;
   private AddressDTOMapper addressDTOMapper;
   private OrderDTOMapper orderDTOMapper;

   @GetMapping(ORDER_GET)
   public OrderDTOs getOrders(@PathVariable String clientId) {
      List<Order> ordersByClientId = orderService.getOrdersByClientId(clientId);
      return OrderDTOs.builder().orders(orderDTOMapper.mapOToDTOList(ordersByClientId)).build();
   }

   @GetMapping(ORDER_GET_DETAILS)
   public OrderPositionDTOs getOrderDetails(@PathVariable Integer orderId) {
      List<OrderPosition> orderPositions = orderService.getOrderPositions(orderId);
      return OrderPositionDTOs.builder().orderPositions(orderDTOMapper.mapOPToDTOList(orderPositions)).build();
   }

   @PostMapping(ORDER_ADD)
   public ResponseEntity<?> addOrder(
       @RequestBody OrderInputDTO orderInputDTO
   ) {
      System.out.println(orderInputDTO);
      Order order = orderService.addOrder(orderInputDTO.getSelected(),
          addressDTOMapper.mapFromDTO(orderInputDTO.getAddressDTO()), orderInputDTO.getRestaurantId());
      return ResponseEntity.created(URI.create(ORDER_GET_DETAILS.replace("{orderId}", order.getId().toString())))
          .build();
   }

   @PostMapping(ORDER_DELETE)
   public ResponseEntity<?> cancelOrder(@PathVariable Integer orderId) {
      // TODO add custom error handling - for cancellation timeout
      orderService.cancelOrder(orderId);
      return ResponseEntity.ok()
          .build();
   }
}