package code.component.manageRestaurant.web.clientOutput;

import code.component.manageRestaurant.domain.MenuPosition;
import code.component.manageRestaurant.domain.MenuPositionDTO;
import code.component.manageRestaurant.domain.mapper.RestaurantDTOMapper;
import code.component.manageRestaurant.manageImages.ImageMapper;
import code.component.manageRestaurant.service.MenuPositionService;
import code.configuration.Constants;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor
public class MenuController {

   public static final String MENU = "menu";
   public static final String MENU_GET = MENU + "/get/{menuId}";

   private MenuPositionService menuPositionService;
   private RestaurantDTOMapper dtoMapper;
   private ImageMapper imageMapper;

   @GetMapping(MENU_GET)
   public String getMenuPositions(
       @PathVariable(value = "menuId") Integer menuId,
       HttpSession session,
       Model model
   ) {
      Integer restaurantId = (Integer) session.getAttribute(Constants.RESTAURANT);
      model.addAttribute("restaurantId", restaurantId);
      List<MenuPosition> allMenuPositions = menuPositionService.getAllMenuPositions(menuId);
      List<MenuPositionDTO> menuPositions = allMenuPositions.stream()
          .map(e -> dtoMapper.mapToDTO(e).withImages(
              imageMapper.mapIToDTOList(e.getImages()))).toList();
      model.addAttribute("menuPositions", menuPositions);
      return "client/menu";
   }

}