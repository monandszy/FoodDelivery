package code.component.manageAccount;

import code.component.manageAccount.domain.Account;
import code.component.manageAccount.domain.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static code.component.manageAccount.domain.Role.ACCOUNT_ROLE.ACCOUNT;

@Service
@AllArgsConstructor
public class AccountService implements UserDetailsService {

   private AccountManagementDAO managementDAO;
   private PasswordEncoder passwordEncoder;

   public String getAuthenticatedUserName() {
      try {
         return SecurityContextHolder.getContext().getAuthentication().getName();
      } catch (Exception e) {
         return "anonymousUser";
      }
   }

   public Account getAuthenticatedAccount() {
      return managementDAO.findByUserName(getAuthenticatedUserName()).orElseThrow();
   }

   @Override
   @Transactional
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Account account = managementDAO.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(username));
      List<GrantedAuthority> authorities = getAccountAuthority(account.getRoles());
      return buildAccountForAuthentication(account, authorities);
   }

   private List<GrantedAuthority> getAccountAuthority(Set<Role> roles) {
      return roles.stream()
          .map(role -> (GrantedAuthority) new SimpleGrantedAuthority(role.getRole().toString()))
          .distinct()
          .toList();
   }

   private UserDetails buildAccountForAuthentication(
       Account account, List<GrantedAuthority> authorities) {
      return new User(
          account.getUserName(),
          account.getPassword(),
          account.getActive(),
          true,
          true,
          true,
          authorities
      );
   }

   @Transactional
   public void register(Account account) {
      managementDAO.findByUserName(account.getUserName()).ifPresent(e ->
      {throw new RuntimeException("Account with that Username already exists");});
      managementDAO.addAccount(account
          .withPassword(passwordEncoder.encode(account.getPassword()))
          .withActive(true)
          .withRoles(Set.of(Role.builder().role(ACCOUNT).build())));
   }

}