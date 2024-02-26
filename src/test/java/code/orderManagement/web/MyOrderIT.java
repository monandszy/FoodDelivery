package code.orderManagement.web;

import code.component.manageAccount.AccountService;
import code.component.manageOrder.OrderService;
import code.component.manageOrder.domain.OrderDTO;
import code.component.manageOrder.domain.OrderPositionDTO;
import code.component.manageOrder.domain.mapper.OrderDTOMapper;
import code.component.manageOrder.web.MyOrderController;
import code.component.manageRestaurant.domain.mapper.RestaurantDTOMapper;
import code.component.manageRestaurant.manageDelivery.domain.Address;
import code.component.manageRestaurant.manageDelivery.domain.AddressDTO;
import code.component.manageRestaurant.manageDelivery.domain.AddressDTOMapper;
import code.util.WebEntityFixtures;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static code.component.manageOrder.web.MyOrderController.ORDER_ADD;
import static code.component.manageOrder.web.MyOrderController.ORDER_DELETE;
import static code.component.manageOrder.web.MyOrderController.ORDER_getByClient;
import static code.component.manageOrder.web.MyOrderController.ORDER_getForClient;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = MyOrderController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MyOrderIT {

   private MockMvc mockMvc;

   @MockBean
   private OrderService orderService;
   @MockBean
   private OrderDTOMapper orderDTOMapper;
   @MockBean
   private RestaurantDTOMapper restaurantDTOMapper;
   @MockBean
   private AddressDTOMapper addressDTOMapper;
   @MockBean
   private AccountService accountService;


   @Test
   void testGetByClient() throws Exception {
      String userName = "client";
      List<OrderDTO> orders = List.of(OrderDTO.builder().id(1).build());
      Mockito.when(accountService.getAuthenticatedUserName()).thenReturn(userName);
      Mockito.when(orderDTOMapper.mapOToDTOList(any())).thenReturn(orders);
      mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8087/" + ORDER_getByClient))
          .andExpect(MockMvcResultMatchers.model().attribute("myOrders", orders))
          .andExpect(MockMvcResultMatchers.view().name("client/order/myOrders"));
      Mockito.verify(orderService).getOrdersByClientId(userName);
   }

   @Test
   void testGetForClient() throws Exception {
      Integer orderId = 1;
      List<OrderPositionDTO> orderPositions = List.of(OrderPositionDTO.builder().id(1).build());
      Mockito.when(orderDTOMapper.mapOPToDTOList(any())).thenReturn(orderPositions);
      mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8087/" +
              ORDER_getForClient.replace("{orderId}", orderId.toString())))
          .andExpect(MockMvcResultMatchers.model().attribute("orderPositions", orderPositions))
          .andExpect(MockMvcResultMatchers.view().name("client/order/myOrder"));
      Mockito.verify(orderService).getOrderPositions(orderId);
   }

   @Test
   void testAdd() throws Exception {
      int restaurantId = 1;
      Integer[] selected = new Integer[]{1};
      AddressDTO addressDTO = WebEntityFixtures.getAddressDTO();
      Address address = Address.builder().id(1).build();
      Mockito.when(addressDTOMapper.mapFromDTO(addressDTO)).thenReturn(address);
      mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8087/" + ORDER_ADD)
              .flashAttr("selectedPositions", selected)
              .sessionAttr("ADDRESS", addressDTO)
              .sessionAttr("RESTAURANT", restaurantId))
          .andExpect(MockMvcResultMatchers.view().name("redirect:myOrders/getOrdersByClientId"));
      Mockito.verify(orderService).addOrder(selected, address, restaurantId);
   }

   @WithMockUser(username = "seller", authorities = {"SELLER"})
   @Test
   void testDelete() throws Exception {
      Integer orderId = 1;
      mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8087/" +
              ORDER_DELETE.replace("{orderId}", orderId.toString())))
          .andExpect(MockMvcResultMatchers.view().name("redirect:myOrders/getOrdersByClientId"))
          .andExpect(MockMvcResultMatchers.model().hasNoErrors())
          .andExpect(MockMvcResultMatchers.status().isFound());
      Mockito.verify(orderService).cancelOrder(orderId);
   }
}