package code.configuration;

import code.component.manageAccount.AccountService;
import code.component.manageAccount.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BootstrapApplicationComponent implements ApplicationListener<ContextRefreshedEvent> {

   private LoginService loginService;
   private AccountService accountService;

   @Override
   public void onApplicationEvent(final @NonNull ContextRefreshedEvent event) {
//      try {
//         Account admin = Account.builder()
//             .userName("admin")
//             .password("admin")
//             .build();
//         loginService.register(admin);
//         accountService.setRole("admin", Role.builder().role(Role.ACCOUNT_ROLE.SELLER).build());
//         accountService.setRole("admin", Role.builder().role(Role.ACCOUNT_ROLE.ADMIN).build());
//         accountService.setRole("admin", Role.builder().role(Role.ACCOUNT_ROLE.CLIENT).build());
//      } catch (Exception e) {}
//      try {
//         Account anonymousUser = Account.builder()
//             .userName("anonymousUser")
//             .password("anonymousUser")
//             .build();
//         loginService.register(anonymousUser);
//         accountService.setRole("anonymousUser", Role.builder().role(Role.ACCOUNT_ROLE.SELLER).build());
//         accountService.setRole("anonymousUser", Role.builder().role(Role.ACCOUNT_ROLE.ADMIN).build());
//         accountService.setRole("anonymousUser", Role.builder().role(Role.ACCOUNT_ROLE.CLIENT).build());
//      } catch (Exception e) {}
   }
}