package code.api.restAssured;

import code.api.orderApi.OrderDTOs;
import code.api.orderApi.OrderInputDTO;
import code.api.orderApi.OrderPositionDTOs;
import code.api.restaurantApi.OpenRestaurantDTO;
import code.component.manageAccount.domain.AccountDTO;
import code.component.manageOrder.domain.Order;
import code.configuration.RestAssuredITBase;
import code.util.ApiFixtures;
import code.util.WebFixtures;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApiIT
    extends RestAssuredITBase
    implements OrderControllerSupport,
    RestaurantControllerSupport,
    AccountControllerSupport
{

   private final MockMvc mockMvc;

   @Test // if not present - some kind of error - throws NotFound
   void testApi() throws Exception {
      AccountDTO account = WebFixtures.getAccount();
      String userName = account.getUserName();
      register(account);

      OpenRestaurantDTO openRestaurantDTO = openRestaurant(ApiFixtures.getOpenRestaurant());

      OrderInputDTO newOrder = OrderInputDTO.builder()
          .addressDTO(WebFixtures.getAddress())
          .restaurantId(openRestaurantDTO.getRestaurant().getId())
          .selected(openRestaurantDTO.getMenuPositions()
              .stream().map(e -> e.getId()).toArray(Integer[]::new))
          .build();
      addOrder(newOrder);

      OrderDTOs orders = getOrders(userName);
      Integer id = orders.getOrders().getFirst().getId();
      OrderPositionDTOs orderDetails = getOrderDetails(id);

      cancelOrder(id);
      OrderDTOs cancelled = getOrders(userName);
      System.out.println(cancelled);
      Assertions.assertEquals(Order.OrderStatus.CANCELLED,
          cancelled.getOrders().getFirst().getStatus());

   }
}