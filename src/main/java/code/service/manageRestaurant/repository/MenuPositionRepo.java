package code.service.manageRestaurant.repository;

import code.service.manageRestaurant.dao.MenuPositionDAO;
import code.service.manageRestaurant.domain.MenuPosition;

import java.util.List;

public class MenuPositionRepo implements MenuPositionDAO {
   @Override
   public void add(MenuPosition menuPosition) {

   }

   @Override
   public List<MenuPosition> getPageByParent(Object parentKey) {
      return null;
   }

   @Override
   public void deleteById(Integer id) {

   }
}