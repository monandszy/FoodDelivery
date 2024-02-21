package code.component.manageRestaurant.web.sellerInput;

import code.component.manageRestaurant.dao.MenuPositionDAO;
import code.component.manageRestaurant.domain.MenuPosition;
import code.component.manageRestaurant.domain.MenuPositionDTO;
import code.component.manageRestaurant.domain.mapper.DTOMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping(MyMenuController.MY_MENU)
public class MyMenuController {

   public static final String MY_MENU = "myMenu";

   MenuPositionDAO menuPositionDAO;
   DTOMapper dtoMapper;

   @GetMapping("/{menuId}")
   public String getMenuPositions(
       @PathVariable(value = "menuId", required = true) Integer menuId,
       @RequestParam(value = "page") Integer page,
       Model model
   ) {
      List<MenuPosition> menu = menuPositionDAO.getPageByParent(menuId, page);
      List<MenuPositionDTO> menuPage = menu.stream().map(dtoMapper::mapToDTO).toList();
      model.addAttribute("menuPage", menuPage);
      return "myMenu";
   }

   void postMenuPosition() {

   }

   void deleteMenuPosition() {

   }
}