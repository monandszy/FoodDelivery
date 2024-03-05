package code.component.manageOrder;

import code.component.manageAccount.AccountService;
import code.component.manageOrder.domain.Order;
import code.component.manageOrder.domain.OrderPosition;
import code.component.manageRestaurant.dao.RestaurantDAO;
import code.component.manageRestaurant.domain.Restaurant;
import code.component.manageRestaurant.manageDelivery.AddressDAO;
import code.component.manageRestaurant.manageDelivery.domain.Address;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

   private OrderDAO orderDAO;
   private RestaurantDAO restaurantDAO;
   private AddressDAO addressDAO;
   private AccountService accountService;

   @Transactional
   public List<OrderPosition> getOrderPositions(Integer orderId) {
      return orderDAO.getOrderPositions(orderId);
   }

   @Transactional
   public Order addOrder(Integer[] selected, Address address, Integer restaurantId) {
      Restaurant restaurant = restaurantDAO.getByRestaurantId(restaurantId);
      address = addressDAO.addOrFindByIp(address);
      Order orderToSave = Order.builder()
          .status(Order.OrderStatus.IN_PROGRESS)
          .timeOfOrder(OffsetDateTime.now()).build();
      Order order = orderDAO.add(orderToSave,
          address.getId(),
          restaurant.getSeller().getUserName(),
          accountService.getAuthenticatedUserName(),
          restaurantId
      );
      List<Integer> selectedList = Arrays.asList(selected);
      orderDAO.addOrderPositions(selectedList.stream().map(selection ->
          OrderPosition.builder().build()).toList(), selectedList, order.getId());
      return order;
   }

   @Transactional
   public void cancelOrder(Integer orderId) {
      Order order = orderDAO.getById(orderId);
      if (order.getStatus() != Order.OrderStatus.IN_PROGRESS)
         throw new RuntimeException("Order status not IN_PROGRESS, cannot cancel");
      if (order.getTimeOfOrder().isBefore(OffsetDateTime.now().minusMinutes(20)))
         throw new RuntimeException("20 minute return timer has already expired");
      orderDAO.delete(orderId);
   }

   @Transactional
   public void complete(Integer orderId) {
      Order order = orderDAO.getById(orderId);
      accountService.getAuthenticatedUserName();
      // TODO verify seller session - restaurantId instead of SellerId in Order might be a bad idea?
      // I need restaurantId for address comparing?
      // add RestaurantEntity - from session and SellerEntity - from DTO?
      orderDAO.updateOrderStatus(order.getId(), Order.OrderStatus.COMPLETED);
   }

   @Transactional
   public List<Order> getIncompleteOrdersBySellerId(String sellerId) {
      return orderDAO.getIncompleteOrdersBySellerId(sellerId);
   }

   @Transactional
   public List<Order> getOrdersByClientId(String clientId) {
      return orderDAO.getOrdersByClientId(clientId);
   }
}