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
           "JOIN FETCH AccountEntity ac on od.seller = ac " +
           "WHERE ac.userName = ?1 AND od.status = 'IN_PROGRESS'"
   )
   List<OrderEntity> findIncompleteBySellerUserName(String sellerId);

   List<OrderEntity> findByClientUserName(String clientId);
}