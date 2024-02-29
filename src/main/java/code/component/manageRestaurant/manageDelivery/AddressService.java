package code.component.manageRestaurant.manageDelivery;

import code.component.manageRestaurant.domain.Restaurant;
import code.component.manageRestaurant.manageDelivery.domain.Address;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService {

   private DeliveryServiceDAO deliveryServiceDAO;

   public List<Restaurant> getPageByAddress(Address address, Integer pageNumber) {
      return List.of();
   }

   public Optional<Address> getAddressByIp(String ip) {
      return Optional.of(Address.builder().build());
   }
}