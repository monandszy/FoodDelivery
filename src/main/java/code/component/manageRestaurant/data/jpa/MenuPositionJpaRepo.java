package code.component.manageRestaurant.data.jpa;

import code.component.manageRestaurant.domain.MenuPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuPositionJpaRepo extends JpaRepository<MenuPositionEntity, Integer> {
}