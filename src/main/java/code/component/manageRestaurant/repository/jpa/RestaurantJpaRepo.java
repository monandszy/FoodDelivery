package code.component.manageRestaurant.repository.jpa;

import code.component.manageRestaurant.domain.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantJpaRepo extends JpaRepository<RestaurantEntity, Integer> {
}