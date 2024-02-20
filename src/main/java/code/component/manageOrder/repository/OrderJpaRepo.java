package code.component.manageOrder.repository;

import code.component.manageOrder.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepo extends JpaRepository<OrderEntity, Integer> {
}