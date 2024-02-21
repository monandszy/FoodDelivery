package restaurantManagement.web;

import code.component.manageRestaurant.dao.MenuDAO;
import code.component.manageRestaurant.dao.MenuPositionDAO;
import code.component.manageRestaurant.web.clientOutput.MenuController;
import code.component.manageRestaurant.web.clientOutput.RestaurantController;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {
    RestaurantController.class,
    MenuController.class
})
//@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientControllerIT {

   private MockMvc mockMvC;

   @MockBean
   private MenuPositionDAO menuPositionDAO;
   @MockBean
   private MenuDAO menuDAO;
}