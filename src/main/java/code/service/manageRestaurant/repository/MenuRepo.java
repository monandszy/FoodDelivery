package code.service.manageRestaurant.repository;

import code.service.manageRestaurant.dao.MenuDAO;
import code.service.manageRestaurant.domain.Menu;

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