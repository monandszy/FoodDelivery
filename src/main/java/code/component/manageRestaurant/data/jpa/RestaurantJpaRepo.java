package code.component.manageRestaurant.data.jpa;

import code.component.manageRestaurant.domain.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantJpaRepo extends JpaRepository<RestaurantEntity, Integer> {
}