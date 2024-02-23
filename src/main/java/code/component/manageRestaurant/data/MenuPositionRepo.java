package code.component.manageRestaurant.data;

import code.component.manageRestaurant.dao.MenuPositionDAO;
import code.component.manageRestaurant.domain.MenuPosition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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

   @Override
   public List<MenuPosition> getMenuPositions(Integer menuId) {
      return null;
   }
}