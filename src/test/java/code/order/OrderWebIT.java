package code.order;

import code.component.manageAccount.AccountService;
import code.component.manageOrder.OrderService;
import code.component.manageOrder.domain.OrderDTO;
import code.component.manageOrder.domain.OrderPositionDTO;
import code.component.manageOrder.domain.mapper.OrderDTOMapper;
import code.component.manageOrder.web.OrderController;
import code.configuration.Constants;
import code.util.WebFixtures;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static code.component.manageOrder.web.OrderController.ORDER_COMPLETE;
import static code.component.manageOrder.web.OrderController.ORDER_getForSeller;
import static code.component.manageOrder.web.OrderController.ORDER_getIncompleteBySeller;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = OrderController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderWebIT {

   private MockMvc mockMvc;

   @MockBean
   private OrderService orderService;
   @MockBean
   private OrderDTOMapper orderDTOMapper;
   @MockBean
   private AccountService accountService;

   @Test
   void testGetBySeller() throws Exception {
      String userName = "seller";
      List<OrderDTO> orders = List.of(OrderDTO.builder().id(1).build());
      Mockito.when(accountService.getAuthenticatedUserName()).thenReturn(userName);
      Mockito.when(orderDTOMapper.mapOToDTOList(any())).thenReturn(orders);
      mockMvc.perform(MockMvcRequestBuilders.get(Constants.URL + ORDER_getIncompleteBySeller))
          .andExpect(MockMvcResultMatchers.model().attribute("orders", orders))
          .andExpect(MockMvcResultMatchers.view().name("seller/order/orders"));
      Mockito.verify(orderService).getIncompleteOrdersBySellerId(userName);
   }

   @Test
   void testGetForSeller() throws Exception {
      Integer orderId = 1;
      List<OrderPositionDTO> orderPositions = List.of(WebFixtures.getOrderPosition());
      Mockito.when(orderDTOMapper.mapOPToDTOList(any())).thenReturn(orderPositions);
      mockMvc.perform(MockMvcRequestBuilders.get(Constants.URL +
              ORDER_getForSeller.replace("{orderId}", orderId.toString())))
          .andExpect(MockMvcResultMatchers.model().attribute("orderPositions", orderPositions))
          .andExpect(MockMvcResultMatchers.view().name("seller/order/order"));
      Mockito.verify(orderService).getOrderPositions(orderId);
   }

   @Test
   void testCompleteOrder() throws Exception {
      Integer orderId = 1;
      mockMvc.perform(MockMvcRequestBuilders.post(Constants.URL +
              ORDER_COMPLETE.replace("{orderId}", orderId.toString())))
          .andExpect(MockMvcResultMatchers.redirectedUrl("/order/getIncompleteBySeller"));
      Mockito.verify(orderService).complete(orderId);
   }

}