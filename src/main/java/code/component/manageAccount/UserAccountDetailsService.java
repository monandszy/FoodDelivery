package code.component.manageAccount;

import code.component.manageAccount.domain.AccountDetailsEntity;
import code.component.manageAccount.domain.RoleEntity;
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
      AccountDetailsEntity account = accountManagementDAO.findByUserName();
      List<GrantedAuthority> authorities = getAccountAuthority(account.getRoles());
      return buildAccountForAuthentication(account, authorities);
   }

   private List<GrantedAuthority> getAccountAuthority(Set<RoleEntity> roles) {
      return roles.stream()
          .map(role -> (GrantedAuthority) new SimpleGrantedAuthority(role.getRole()))
          .distinct()
          .toList();
   }

   private UserDetails buildAccountForAuthentication(
       AccountDetailsEntity account, List<GrantedAuthority> authorities) {
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