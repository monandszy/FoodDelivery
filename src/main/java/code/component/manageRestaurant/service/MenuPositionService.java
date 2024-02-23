package code.component.manageRestaurant.service;

import code.component.manageRestaurant.dao.MenuPositionDAO;
import code.component.manageRestaurant.domain.MenuPosition;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MenuPositionService {
   private MenuPositionDAO menuPositionDAO;

   public void add(MenuPosition menuPosition) {
      menuPositionDAO.add(menuPosition);
   }

   public List<MenuPosition> getPageByMenuId(Object menuId, Integer page) {
      return menuPositionDAO.getPageByParent(menuId, page);
   }

   public void deleteById(Integer id) {
      menuPositionDAO.deleteById(id);
   }

   public List<MenuPosition> getAllMenuPositions(Integer menuId) {
      return menuPositionDAO.getMenuPositions(menuId);
   }
}