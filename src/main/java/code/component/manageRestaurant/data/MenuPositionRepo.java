package code.component.manageRestaurant.data;

import code.component.manageRestaurant.dao.MenuPositionDAO;
import code.component.manageRestaurant.data.jpa.MenuPositionJpaRepo;
import code.component.manageRestaurant.domain.MenuPosition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuPositionRepo implements MenuPositionDAO {

   MenuPositionJpaRepo menuPositionJpaRepo;

   @Override
   public void add(MenuPosition menuPosition) {

   }

   @Override
   public List<MenuPosition> getPageByParent(Object parentKey, Integer page) {
      return List.of();
   }

   @Override
   public void deleteById(Integer id) {
   }

   @Override
   public List<MenuPosition> getMenuPositions(Integer menuId) {
      return List.of();
   }
}