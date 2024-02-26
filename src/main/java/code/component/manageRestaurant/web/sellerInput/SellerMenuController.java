package code.component.manageRestaurant.web.sellerInput;

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
public class SellerMenuController {

   public static final String MY_MENU = "myMenu";
   public static final String MY_MENU_GET = MY_MENU + "/get/{menuId}";
   public static final String MY_MENU_ADD = MY_MENU + "/add";
   public static final String MY_MENU_DELETE = MY_MENU + "/delete/{menuPositionId}";

   private MenuPositionService menuPositionService;
   private RestaurantDTOMapper dtoMapper;

   @GetMapping(MY_MENU_GET)
   public String getMenuViewById(
       @PathVariable(value = "menuId") String menuId,
       @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
       @RequestParam(value = "restaurantId", required = false) Integer restaurantId,
       Model model
   ) {
      model.addAttribute("menuPositionDTO", MenuPositionDTO.builder().build());
      model.addAttribute("menuId", menuId);
      model.addAttribute("restaurantId", restaurantId);
      pageNumber = Objects.isNull(pageNumber) ? Integer.valueOf(START_PAGE) : pageNumber;
      model.addAttribute("pageNumber", pageNumber);
      List<MenuPositionDTO> menuPage = dtoMapper.mapMPToDTOList(
          menuPositionService.getPageByMenu(Integer.valueOf(menuId), pageNumber));
      model.addAttribute("menuPage", menuPage);
      return "seller/myMenu";
   }

   @PostMapping(MY_MENU_ADD)
   public String postMenuPosition(
       @ModelAttribute("menuPositionDTO") MenuPositionDTO menuPositionDTO,
       @RequestParam("menuId") String menuId
   ) {
      menuPositionService.add(dtoMapper.mapFromDTO(menuPositionDTO), Integer.valueOf(menuId));
      return "redirect:/myMenu/get/%s".formatted(menuId);
   }

   @DeleteMapping(MY_MENU_DELETE)
   public String deleteMenuPosition(
       @PathVariable("menuPositionId") String menuPositionId,
       @RequestParam("menuId") String menuId
   ) {
      menuPositionService.deleteById(Integer.valueOf(menuPositionId));
      return "redirect:/myMenu/get/%s".formatted(menuId);
   }
}