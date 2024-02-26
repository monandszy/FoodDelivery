package code.component.manageOrder.data;

import code.component.manageOrder.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderJpaRepo extends JpaRepository<OrderEntity, Integer> {
   @Query(
       "SELECT od FROM OrderEntity od " +
           "JOIN FETCH RestaurantEntity re on re.id = od.restaurantId " +
           "JOIN FETCH AccountEntity ac on re.seller = ac " +
           "WHERE ac.id = ?1 AND od.status = 'IN_PROGRESS'"
   )
   List<OrderEntity> findIncompleteBySeller_UserName(String sellerId);

   List<OrderEntity> findByClient_UserName(String clientId);
}