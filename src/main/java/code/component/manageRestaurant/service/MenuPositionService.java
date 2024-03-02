package code.component.manageRestaurant.service;

import code.component.manageRestaurant.dao.MenuPositionDAO;
import code.component.manageRestaurant.domain.MenuPosition;
import code.component.manageRestaurant.manageImages.ImageDAO;
import code.component.manageRestaurant.manageImages.ImageDTO;
import code.component.manageRestaurant.manageImages.ImageMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class MenuPositionService {
   private MenuPositionDAO menuPositionDAO;
   private ImageDAO imageDAO;
   private ImageMapper imageMapper;

   @Transactional
   public void add(ImageDTO image, MenuPosition menuPosition, Integer menuId) {
      MenuPosition created = menuPositionDAO.add(menuPosition, menuId);
      if (Objects.nonNull(image))
         imageDAO.add(imageMapper.mapToEntity(image), created.getId());
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