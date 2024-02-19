package code.component.manageRestaurant.service;

import code.component.manageRestaurant.dao.MenuPositionDAO;
import code.component.manageRestaurant.domain.MenuPosition;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class MenuPositionService {
   private MenuPositionDAO menuPositionDAO;

   @Transactional
   public void add(MenuPosition menuPosition, Integer menuId) {
      menuPositionDAO.add(menuPosition, menuId);
   }

   @Transactional
   public List<MenuPosition> getPageByMenu(Integer menuId, Integer page) {
      return menuPositionDAO.getPageByMenuId(menuId, page);
   }

   @Transactional
   public void deleteById(Integer id) {
      menuPositionDAO.deleteById(id);
   }

   @Transactional
   public List<MenuPosition> getAllMenuPositions(Integer menuId) {
      return menuPositionDAO.getMenuPositions(menuId);
   }
}