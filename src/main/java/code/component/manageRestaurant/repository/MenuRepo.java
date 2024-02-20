package code.component.manageRestaurant.repository;

import code.component.manageRestaurant.dao.MenuDAO;
import code.component.manageRestaurant.domain.Menu;

import java.util.List;

public class MenuRepo implements MenuDAO {
   @Override
   public void add(Menu menu) {

   }

   @Override
   public List<Menu> getPageByParent(Object parentKey) {
      return null;
   }

   @Override
   public void deleteById(Integer id) {

   }
}