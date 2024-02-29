package code.component.manageRestaurant.manageDelivery;

import code.api.ipAddressApi.ApiClientImpl;
import code.component.manageAccount.AccountService;
import code.component.manageRestaurant.domain.Restaurant;
import code.component.manageRestaurant.manageDelivery.domain.Address;
import code.component.manageRestaurant.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService {

   private AddressDAO addressDAO;
   private AccountService accountService;
   private RestaurantService restaurantService;
   private ApiClientImpl apiClient;

   public List<Restaurant> getPageByAddress(Address clientAddress, Integer pageNumber) {
      // calculate proximity to other addresses.
      // get list of restaurants of nearest proximity that are withing range
      // get restaurant re where distance orderby min
      //                         and re.range < distance

      // Placeholder
      return restaurantService.getPageBySellerId(
          accountService.getAuthenticatedUserName(), 0);
   }

   public Address getAddress(String ip) {
      if (Objects.isNull(ip))
         ip = accountService.getCurrentIp();
      Optional<Address> address = addressDAO.getByIp(ip);
      if (address.isPresent()) {
         return address.get();
      } else {
         Optional<Address> byIp = addressDAO.getByIp(ip);
         return apiClient.getAddressFromApi(ip);
      }
   }

}