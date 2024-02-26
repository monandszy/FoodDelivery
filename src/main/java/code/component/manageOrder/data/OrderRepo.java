package code.component.manageOrder.data;

import code.component.manageAccount.domain.mapper.AccountEntityMapper;
import code.component.manageOrder.OrderDAO;
import code.component.manageOrder.domain.Order;
import code.component.manageOrder.domain.OrderEntity;
import code.component.manageOrder.domain.OrderPosition;
import code.component.manageOrder.domain.mapper.OrderEntityMapper;
import code.component.manageRestaurant.manageDelivery.domain.AddressEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class OrderRepo implements OrderDAO {

   OrderJpaRepo orderJpaRepo;
   OrderEntityMapper orderEntityMapper;
   AddressEntityMapper addressEntityMapper;
   AccountEntityMapper accountEntityMapper;
   OrderPositionJpaRepo orderPositionJpaRepo;

   @Override
   public Order getOrderById(Integer orderId) {
      return orderEntityMapper.mapFromEntity(orderJpaRepo.findById(orderId)
          .orElseThrow(() -> new RuntimeException("Could not find Order")));
   }

   @Override
   public List<OrderPosition> getOrderPositions(Integer orderId) {
      return orderEntityMapper.mapOPFromEntityList(orderPositionJpaRepo.findByOrderId(orderId));
   }

   @Override
   public void addOrder(Order order) {
      OrderEntity entity = orderEntityMapper.mapToEntity(order);
      entity.setAddress(addressEntityMapper.mapToEntity(order.getAddress()));
      entity.setClient(accountEntityMapper.mapToEntity(order.getClient()));
      orderJpaRepo.save(entity);
   }

   @Override
   public void cancelOrder(Integer orderId) {
      // TODO - separate list for completed seller orders - move to CANCELLED status
      orderJpaRepo.deleteById(orderId);
   }

   @Override
   public void completeOrder(Order order) {
      // should update it, i think?
      OrderEntity entity = orderEntityMapper.mapToEntity(order);
   }

   @Override
   public List<Order> getIncompleteOrdersBySellerId(String sellerId) {
      return orderEntityMapper.mapOFromEntityList(orderJpaRepo.findIncompleteBySeller_UserName(sellerId));
   }

   @Override
   public List<Order> getOrdersByClientId(String clientId) {
      return orderEntityMapper.mapOFromEntityList(orderJpaRepo.findByClient_UserName(clientId));
   }
}