package code.component.manageRestaurant.service;

import code.component.manageRestaurant.dao.MenuDAO;
import code.component.manageRestaurant.domain.Menu;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class MenuService {

   private MenuDAO menuDAO;

   @Transactional
   public void add(Menu menu) {
      menuDAO.add(menu);
   }

   @Transactional
   public List<Menu> getPageByRestaurantId(Integer restaurantId, Integer page) {
      return menuDAO.getPageByParent(restaurantId, page);
   }

   @Transactional
   public void deleteById(Integer id) {
      menuDAO.deleteById(id);
   }
}