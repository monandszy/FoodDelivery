package code.component.manageOrder.data;

import code.component.manageOrder.domain.OrderPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderPositionJpaRepo extends JpaRepository<OrderPositionEntity, Integer> {
   List<OrderPositionEntity> findByOrderId(Integer id);
}