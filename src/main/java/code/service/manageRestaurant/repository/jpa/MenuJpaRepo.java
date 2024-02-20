package code.service.manageRestaurant.repository.jpa;

import code.service.manageRestaurant.domain.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuJpaRepo extends JpaRepository<MenuEntity, Integer> {
}