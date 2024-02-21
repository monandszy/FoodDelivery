package code.component.manageOrder.data;

import code.component.manageOrder.domain.OrderPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPositionJpaRepo extends JpaRepository<OrderPositionEntity, Integer> {
}