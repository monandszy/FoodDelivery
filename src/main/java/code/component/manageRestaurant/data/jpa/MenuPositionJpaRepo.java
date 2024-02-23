package code.component.manageRestaurant.data.jpa;

import code.component.manageRestaurant.domain.MenuEntity;
import code.component.manageRestaurant.domain.MenuPositionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuPositionJpaRepo extends JpaRepository<MenuPositionEntity, Integer> {
   Page<MenuPositionEntity> getPageByMenu(MenuEntity menu, Pageable pageable);
}