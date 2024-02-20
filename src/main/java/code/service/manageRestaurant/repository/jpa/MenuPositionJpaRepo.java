package code.service.manageRestaurant.repository.jpa;

import code.service.manageRestaurant.domain.MenuPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuPositionJpaRepo extends JpaRepository<MenuPositionEntity, Integer> {
}