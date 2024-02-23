package code.component.manageRestaurant.service;

import code.component.manageRestaurant.dao.ServiceDAO;
import code.component.manageRestaurant.domain.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProximityCalculationService {

   private ServiceDAO serviceDAO;

   List<Restaurant> getRestaurantsBasedOnProximity(Object address) {
      return List.of();
   }

}