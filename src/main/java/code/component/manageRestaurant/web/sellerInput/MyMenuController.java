package code.component.manageRestaurant.web.sellerInput;

import code.component.manageRestaurant.domain.MenuPosition;
import code.component.manageRestaurant.domain.MenuPositionDTO;
import code.component.manageRestaurant.domain.mapper.RestaurantDTOMapper;
import code.component.manageRestaurant.service.MenuPositionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

import static code.configuration.Constants.START_PAGE;


@Controller
@AllArgsConstructor
public class MyMenuController {

   public static final String MY_MENU = "myMenu";
   public static final String MY_MENU_GET = MY_MENU + "/get/{menuId}";
   public static final String MY_MENU_ADD = MY_MENU + "/add";
   public static final String MY_MENU_DELETE = MY_MENU + "/delete/{menuPositionId}";

   private MenuPositionService menuPositionService;
   private RestaurantDTOMapper dtoMapper;

   @GetMapping(MY_MENU_GET)
   public String getMenuViewById(
       @PathVariable(value = "menuId") Integer menuId,
       @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
       @RequestParam(value = "restaurantId") Integer restaurantId,
       Model model
   ) {
      model.addAttribute("menuPositionDTO", MenuPositionDTO.builder().build());
      model.addAttribute("menuId", menuId);
      model.addAttribute("restaurantId", restaurantId);
      pageNumber = Objects.isNull(pageNumber) ? Integer.valueOf(START_PAGE) : pageNumber;
      model.addAttribute("pageNumber", pageNumber);
      List<MenuPosition> menu = menuPositionService.getPageByMenu(menuId, pageNumber);
      List<MenuPositionDTO> menuPage = menu.stream().map(dtoMapper::mapToDTO).toList();
      model.addAttribute("menuPage", menuPage);
      return "seller/myMenu";
   }

   @PostMapping(MY_MENU_ADD)
   public String postMenuPosition(
       @ModelAttribute("menuPositionDTO") MenuPositionDTO menuPositionDTO,
       @RequestParam("menuId") Integer menuId
   ) {
      menuPositionService.add(dtoMapper.mapFromDTO(menuPositionDTO), menuId);
      return "redirect:/myMenu/get/%s".formatted(menuId);
   }

   @DeleteMapping(MY_MENU_DELETE)
   public String deleteMenuPosition(
       @PathVariable("menuPositionId") Integer menuPositionId,
       @RequestParam("menuId") Integer menuId
   ) {
      menuPositionService.deleteById(menuPositionId);
      return "redirect:/myMenu/get/%s".formatted(menuId);
   }
}