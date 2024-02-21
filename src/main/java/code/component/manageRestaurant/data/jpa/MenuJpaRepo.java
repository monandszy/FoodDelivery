package code.component.manageRestaurant.data.jpa;

import code.component.manageRestaurant.domain.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuJpaRepo extends JpaRepository<MenuEntity, Integer> {
}