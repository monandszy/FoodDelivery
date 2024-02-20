package code.service.manageOrder.repository;

import code.service.manageOrder.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepo extends JpaRepository<OrderEntity, Integer> {
}