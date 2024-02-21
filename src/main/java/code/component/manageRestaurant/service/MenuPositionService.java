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

   }

   public List<MenuPosition> getPageByParent(Object parentKey, Integer page) {
      return null;
   }

   public void deleteById(Integer id) {

   }
}