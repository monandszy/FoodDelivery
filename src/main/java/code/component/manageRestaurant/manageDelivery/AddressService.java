package code.component.manageRestaurant.manageDelivery;

import code.component.api.ipAddressApi.ApiClientImpl;
import code.component.manageAccount.AccountService;
import code.component.manageRestaurant.dao.RestaurantDAO;
import code.component.manageRestaurant.domain.Restaurant;
import code.component.manageRestaurant.manageDelivery.domain.Address;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressService {

   private AddressDAO addressDAO;
   private RestaurantDAO restaurantDAO;
   private AccountService accountService;
   private ApiClientImpl apiClient;
   private DistanceCalculationService distanceCalculationService;

   public List<Restaurant> getPageByAddress(Address clientAddress, Integer pageNumber) {
      List<Restaurant> restaurants = restaurantDAO.getAllWithAddress();
      Double longitude = clientAddress.getLongitude();
      Double latitude = clientAddress.getLatitude();
      Map<Double, Restaurant> lowestDistance = new HashMap<>();
      for (Restaurant restaurant : restaurants) {
         Double distance = distanceCalculationService.calculateDistance(
             longitude, latitude,
             restaurant.getAddress().getLongitude(),
             restaurant.getAddress().getLatitude());
         if ((restaurant.getDeliveryRange() - distance > 0)) {
            lowestDistance.put(distance, restaurant);
         }
      }
      return lowestDistance.entrySet().stream()
          .sorted(Comparator.comparing(e -> e.getKey()))
          .map(e -> e.getValue())
          .skip(pageNumber*10).limit(10).collect(Collectors.toList());

      // TODO Maybe one day + consider moving logic to a database query
//      return restaurants.stream().collect(Collectors.teeing(
//          Collectors.mapping(e -> e, Collectors.fi),
//              e -> {
//                 Double distance = distanceCalculationService.calculateDistance(
//                     longitude, latitude,
//                     e.getAddress().getLongitude(),
//                     e.getAddress().getLatitude());
//                 if (e.getDeliveryRange() - distance < 0) return -1D;
//                 return distance;
//              })
//          )).skip(pageNumber * 10).limit(10).toList();
   }

   public Address getAddress(String ip) {
      if (Objects.isNull(ip))
         ip = accountService.getCurrentIp();
      Optional<Address> address = addressDAO.getByIp(ip);
      if (address.isPresent()) {
         return address.get();
      } else {
         return apiClient.getAddressFromApi(ip);
      }
   }

}