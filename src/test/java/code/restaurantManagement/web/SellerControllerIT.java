package code.restaurantManagement.web;

import code.component.manageRestaurant.service.MenuPositionService;
import code.component.manageRestaurant.service.MenuService;
import code.component.manageRestaurant.service.RestaurantService;
import code.component.manageRestaurant.web.sellerInput.MyMenuController;
import code.component.manageRestaurant.web.sellerInput.MyRestaurantController;
import code.component.manageRestaurant.web.sellerInput.MyRestaurantsController;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {
    MyRestaurantsController.class,
    MyRestaurantController.class,
    MyMenuController.class
})
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SellerControllerIT {

   private MockMvc mockMvC;

   @MockBean
   private RestaurantService restaurantService;
   @MockBean
   private MenuPositionService menuPositionService;
   @MockBean
   private MenuService menuService;

   @Test
   void test() {

   }

}