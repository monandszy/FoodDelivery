package code.component.manageOrder.data;

import code.component.manageOrder.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepo extends JpaRepository<OrderEntity, Integer> {
}