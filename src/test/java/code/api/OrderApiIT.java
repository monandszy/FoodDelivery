package code.api;

import code.api.orderApi.OrderRestController;
import code.component.manageOrder.OrderService;
import code.component.manageOrder.domain.OrderDTO;
import code.component.manageOrder.domain.mapper.OrderDTOMapper;
import code.component.manageRestaurant.manageDelivery.domain.AddressDTOMapper;
import code.configuration.Constants;
import code.util.WebFixtures;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static code.api.orderApi.OrderRestController.ORDER_GET;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {OrderRestController.class})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderApiIT {

   private final MockMvc mockMvc;

   @MockBean
   private final OrderService orderService;
   @MockBean
   private final AddressDTOMapper addressDTOMapper;
   @MockBean
   private final OrderDTOMapper orderDTOMapper;

   private ObjectMapper objectMapper = new ObjectMapper();

   @Test
   void testGetOrders() throws Exception {
      String clientId = "test";
      OrderDTO order = WebFixtures.getOrder();
      List<OrderDTO> orders = List.of(order);
      Mockito.when(orderDTOMapper.mapOToDTOList(any())).thenReturn(orders);
      mockMvc.perform(get(Constants.URL + ORDER_GET, clientId))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.orders.*.restaurantId",
              Matchers.containsInAnyOrder(order.getRestaurantId())));
   }

   @Test
   void testGetDetails() throws Exception {
   }

   @Test
   void testAddOrder() {

   }

   @Test
   void testCancelOrder() {

   }

}