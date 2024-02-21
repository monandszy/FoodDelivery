package code.component.manageRestaurant.service;

import code.component.manageRestaurant.dao.MenuDAO;
import code.component.manageRestaurant.domain.Menu;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MenuService {

   private MenuDAO menuDAO;

   public void add(Menu menu) {

   }

   public List<Menu> getPageByParent(Object parentKey, Integer page) {
      return null;
   }

   public void deleteById(Integer id) {

   }
}