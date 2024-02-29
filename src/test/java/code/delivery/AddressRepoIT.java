package code.delivery;

import code.component.manageRestaurant.manageDelivery.AddressJpaRepo;
import code.component.manageRestaurant.manageDelivery.AddressRepo;
import code.component.manageRestaurant.manageDelivery.domain.Address;
import code.component.manageRestaurant.manageDelivery.domain.AddressEntity;
import code.component.manageRestaurant.manageDelivery.domain.AddressEntityMapperImpl;
import code.configuration.AbstractJpaIT;
import code.util.DataFixtures;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

@Import(value = {
    AddressRepo.class,
    AddressEntityMapperImpl.class
})
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AddressRepoIT extends AbstractJpaIT {

   private AddressRepo addressRepo;
   private AddressJpaRepo addressJpaRepo;

   @Test
   public void testAddOrFind() {
      Address someAddress = DataFixtures.getAddress();
      Address address1 = addressRepo.addOrFind(someAddress);
      List<AddressEntity> all1 = addressJpaRepo.findAll();
      Assertions.assertTrue(all1.size() == 1);
      Address address2 = addressRepo.addOrFind(someAddress);
      Assertions.assertEquals(someAddress, address2);
      List<AddressEntity> all2 = addressJpaRepo.findAll();
      Assertions.assertTrue(all2.size() == 1);
   }

   @Test
   public void testGetByIp() {
      String ip = "ip";
      Address address = DataFixtures.getAddress().withIpAddress(ip);
      addressRepo.addOrFind(address);
      Optional<Address> byIp = addressRepo.getByIp(ip);
      Assertions.assertTrue(byIp.isPresent());
      Assertions.assertEquals(address, byIp.get());
   }

}