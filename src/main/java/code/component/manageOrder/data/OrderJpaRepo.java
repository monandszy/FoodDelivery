package code.component.manageOrder.data;

import code.component.manageOrder.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepo extends JpaRepository<OrderEntity, Integer> {
}