package code.configuration;

import code.component.manageAccount.ManageAccountService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BootstrapApplicationComponent implements ApplicationListener<ContextRefreshedEvent> {

   private ManageAccountService manageAccountService;

   @Override
   public void onApplicationEvent(final @NonNull ContextRefreshedEvent event) {
      // init data
   }
}