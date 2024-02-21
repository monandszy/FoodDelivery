package code.component.manageRestaurant.data;

import code.component.manageRestaurant.dao.MenuPositionDAO;
import code.component.manageRestaurant.domain.MenuPosition;

import java.util.List;

public class MenuPositionRepo implements MenuPositionDAO {
   @Override
   public void add(MenuPosition menuPosition) {

   }

   @Override
   public List<MenuPosition> getPageByParent(Object parentKey, Integer page) {
      return null;
   }

   @Override
   public void deleteById(Integer id) {

   }
}