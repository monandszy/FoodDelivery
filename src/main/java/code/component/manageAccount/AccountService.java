package code.component.manageAccount;

import code.api.ipAddressApi.ApiDAO;
import code.component.manageAccount.domain.Account;
import code.component.manageAccount.domain.Role;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

   private AccountDAO accountDAO;
   private PasswordEncoder passwordEncoder;
   private HttpServletRequest request;

   public String getAuthenticatedUserName() {
      try {
         return SecurityContextHolder.getContext().getAuthentication().getName();
      } catch (Exception e) {
         return "anonymousUser";
      }
   }

   public String getCurrentIp() {
      String remoteAddr = "";
      request.getRemoteAddr();
      if (request != null) {
         remoteAddr = request.getHeader("X-FORWARDED-FOR");
         if (remoteAddr == null || "".equals(remoteAddr)) {
            remoteAddr = request.getRemoteAddr();
         }
      }
      if (remoteAddr.equals("127.0.0.1")) {
         return ApiDAO.TEST_IP;
      }
      return remoteAddr;
   }

   public List<Account> getAccountPage(Integer pageNumber) {
      return accountDAO.getAccountPage(pageNumber);
   }

   public void deleteAccount(String userName) {
      accountDAO.deleteByUserName(userName);
   }

   public void setRole(String userName, Role role) {
      accountDAO.setRole(userName, role);
   }
}