package code.component.manageRestaurant.repository.jpa;

import code.component.manageRestaurant.domain.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuJpaRepo extends JpaRepository<MenuEntity, Integer> {
}