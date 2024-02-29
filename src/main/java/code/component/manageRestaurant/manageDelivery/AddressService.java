package code.component.manageRestaurant.manageDelivery;

import code.api.ipAddressApi.ApiClientImpl;
import code.component.manageAccount.AccountService;
import code.component.manageRestaurant.domain.Restaurant;
import code.component.manageRestaurant.manageDelivery.domain.Address;
import code.component.manageRestaurant.manageDelivery.domain.AddressDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService {

   private AddressDAO addressDAO;
   private final AccountService accountService;
   private ApiClientImpl apiClient;

   public List<Restaurant> getPageByAddress(Address address, Integer pageNumber) {
      return null;
   }

   public Optional<Address> getAddressByIp(String ip) {
      return addressDAO.getByIp(ip);
   }

   public AddressDTO getAddress(String ip) {
      if (Objects.isNull(ip))
         ip = accountService.getCurrentIp();
      AddressDTO address = apiClient.getAddressDTO(ip);
      return address;
   }

}