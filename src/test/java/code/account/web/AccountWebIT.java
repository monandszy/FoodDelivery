package code.account.web;

import code.component.manageAccount.AccountService;
import code.component.manageAccount.domain.AccountDTO;
import code.component.manageAccount.domain.Role;
import code.component.manageAccount.domain.RoleDTO;
import code.component.manageAccount.domain.mapper.AccountDTOMapper;
import code.component.manageAccount.web.HomeController;
import code.configuration.Constants;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static code.component.manageAccount.web.HomeController.ACCOUNTS;
import static code.component.manageAccount.web.HomeController.ACCOUNTS_DELETE;
import static code.component.manageAccount.web.HomeController.ACCOUNTS_SET_ROLE;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(controllers = HomeController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountWebIT {

   private MockMvc mockMvc;

   @MockBean
   private AccountService accountService;
   @MockBean
   private AccountDTOMapper accountDTOMapper;

   @Test
   void testGetAccounts() throws Exception {
      List<AccountDTO> accounts = List.of(AccountDTO.builder().id(1).build());
      Mockito.when(accountDTOMapper.mapAToDTOList(any())).thenReturn(accounts);
      mockMvc.perform(MockMvcRequestBuilders.get(Constants.URL + ACCOUNTS))
          .andExpect(model().attribute("accountPage", accounts))
          .andExpect(model().attribute("pageNumber", 0))
          .andExpect(MockMvcResultMatchers.view().name("home"));
      Mockito.verify(accountService).getAccountPage(0);
   }

   @Test
   void testSetRole() throws Exception {
      String userName = "test";
      RoleDTO role = RoleDTO.builder().role(Role.ACCOUNT_ROLE.ACCOUNT).build();
      mockMvc.perform(MockMvcRequestBuilders.post(Constants.URL
          + ACCOUNTS_SET_ROLE.replace("{userName}", userName))
              .flashAttr("role", role))
          .andExpect(MockMvcResultMatchers.redirectedUrl("/home"));
      Mockito.verify(accountService).setRole(userName, null);
   }

   @Test
   void testDeleteAccount() throws Exception {
      String userName = "test";
      mockMvc.perform(MockMvcRequestBuilders.post(Constants.URL
                  + ACCOUNTS_DELETE.replace("{userName}", userName)))
          .andExpect(MockMvcResultMatchers.redirectedUrl("/home"));
      Mockito.verify(accountService).deleteAccount(userName);
   }
}