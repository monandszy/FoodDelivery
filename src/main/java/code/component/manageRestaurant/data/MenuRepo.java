package code.component.manageRestaurant.data;

import code.component.manageRestaurant.dao.MenuDAO;
import code.component.manageRestaurant.domain.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuRepo implements MenuDAO {
   @Override
   public void add(Menu menu) {

   }

   @Override
   public List<Menu> getPageByParent(Object parentKey, Integer page) {
      return null;
   }

   @Override
   public void deleteById(Integer id) {

   }
}