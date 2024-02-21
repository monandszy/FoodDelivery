package restaurantManagement.web;

import code.component.manageRestaurant.dao.MenuDAO;
import code.component.manageRestaurant.dao.MenuPositionDAO;
import code.component.manageRestaurant.dao.RestaurantDAO;
import code.component.manageRestaurant.web.sellerInput.MyMenuController;
import code.component.manageRestaurant.web.sellerInput.MyRestaurantController;
import code.component.manageRestaurant.web.sellerInput.MyRestaurantsController;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {
    MyRestaurantsController.class,
    MyRestaurantController.class,
    MyMenuController.class
})
//@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SellerControllerIT {

   private MockMvc mockMvC;

   @MockBean
   private RestaurantDAO restaurantDAO;
   @MockBean
   private MenuPositionDAO menuPositionDAO;
   @MockBean
   private MenuDAO menuDAO;
}