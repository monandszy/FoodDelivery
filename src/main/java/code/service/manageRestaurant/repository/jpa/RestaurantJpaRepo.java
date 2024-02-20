package code.service.manageRestaurant.repository.jpa;

import code.service.manageRestaurant.domain.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantJpaRepo extends JpaRepository<RestaurantEntity, Integer> {
}