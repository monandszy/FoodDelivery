package code.component.manageAccount;

import code.component.manageAccount.domain.Account;
import code.component.manageAccount.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserAccountDetailsService implements UserDetailsService {

   private final AccountManagementDAO accountManagementDAO;

   @Override
   @Transactional
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Account account = accountManagementDAO.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(username));
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
}