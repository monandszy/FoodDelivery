package code.restaurantManagement.data;

import code.component.manageAccount.domain.mapper.AccountEntityMapper;
import code.component.manageOrder.data.OrderJpaRepo;
import code.component.manageOrder.data.OrderPositionJpaRepo;
import code.component.manageOrder.data.OrderRepo;
import code.component.manageOrder.domain.mapper.OrderEntityMapper;
import code.component.manageRestaurant.manageDelivery.domain.AddressEntityMapper;
import code.configuration.AbstractJpaIT;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

@Import(value = {
    OrderRepo.class,
    OrderJpaRepo.class,
    OrderEntityMapper.class,
    AddressEntityMapper.class,
    AccountEntityMapper.class,
    OrderPositionJpaRepo.class
})
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderRepoIT extends AbstractJpaIT {

   private OrderRepo orderRepo;
   private OrderJpaRepo orderJpaRepo;
   private OrderEntityMapper orderEntityMapper;
   private AddressEntityMapper addressEntityMapper;
   private AccountEntityMapper accountEntityMapper;
   private OrderPositionJpaRepo orderPositionJpaRepo;

   void testGet() {
      orderRepo.getOrderById()

      orderRepo.getOrderPositions()

      orderRepo.getOrdersByClientId();
      orderRepo.getIncompleteOrdersBySellerId();

   }

   void testAdd() {
      orderRepo.addOrder();

   }

   void testUpdate() {
      orderRepo.completeOrder();
      orderRepo.cancelOrder();

   }

}