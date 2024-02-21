package code.component.manageRestaurant.data.jpa;

import code.component.manageRestaurant.domain.MenuPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuPositionJpaRepo extends JpaRepository<MenuPositionEntity, Integer> {
}