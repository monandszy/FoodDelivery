package code.component.manageRestaurant.data;

import code.component.manageAccount.data.AccountJpaRepo;
import code.component.manageAccount.domain.AccountEntity;
import code.component.manageAccount.domain.mapper.AccountEntityMapper;
import code.component.manageRestaurant.dao.RestaurantDAO;
import code.component.manageRestaurant.data.jpa.RestaurantJpaRepo;
import code.component.manageRestaurant.domain.Restaurant;
import code.component.manageRestaurant.domain.RestaurantEntity;
import code.component.manageRestaurant.domain.mapper.RestaurantEntityMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

import static code.configuration.Constants.PAGE_SIZE;

@Repository
@AllArgsConstructor
public class RestaurantRepo implements RestaurantDAO {

   private RestaurantJpaRepo restaurantJpaRepo;
   private AccountJpaRepo accountJpaRepo;
   private RestaurantEntityMapper entityMapper;
   private AccountEntityMapper accountEntityMapper;

   public Restaurant add(Restaurant restaurant, String sellerId) {
      // TODO add address adding
      RestaurantEntity save = entityMapper.mapToEntity(restaurant);
      System.out.println(save.getId());
      save.setSeller(accountJpaRepo.findByUserName(sellerId)
          .orElseThrow(() -> new EntityNotFoundException("account not found")));
      return entityMapper.mapFromEntity(restaurantJpaRepo.save(save));
   }

   @Override
   public Restaurant getByRestaurantId(Integer restaurantId) {
      RestaurantEntity restaurant = restaurantJpaRepo.findById(restaurantId).orElseThrow();
      return entityMapper.mapFromEntity(restaurant).withSeller(
          accountEntityMapper.mapFromEntity(restaurant.getSeller()));
   }

   @Override
   public void deleteById(Integer id) {
      restaurantJpaRepo.deleteById(id);
   }

   @Override
   public List<Restaurant> getPageBySeller(String sellerId, Integer page) {
      AccountEntity seller = accountJpaRepo.findByUserName(sellerId).orElseThrow();
      Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by("id"));
      Page<RestaurantEntity> pageBySeller = restaurantJpaRepo.getPageBySeller(seller, pageable);
      return pageBySeller.getContent().stream().map(entityMapper::mapFromEntity).toList();
   }

}