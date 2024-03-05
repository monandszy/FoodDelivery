package code.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder(16);
   }

   @Bean
   public DaoAuthenticationProvider authProvider(
       PasswordEncoder passwordEncoder,
       UserDetailsService userDetailsService
   ) {
      DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
      provider.setPasswordEncoder(passwordEncoder);
      provider.setUserDetailsService(userDetailsService);
      return provider;
   }

   @Bean
   public AuthenticationManager authManager(
       HttpSecurity http,
       AuthenticationProvider authProvider
   ) throws Exception {
      return http.getSharedObject(AuthenticationManagerBuilder.class)
          .authenticationProvider(authProvider)
          .build();
   }

   @Bean
   @ConditionalOnProperty(value = "spring.security.enabled",
       havingValue = "true", matchIfMissing = true)
   SecurityFilterChain securityEnabled(HttpSecurity http) throws Exception {
      return http
          .csrf((AbstractHttpConfigurer::disable))
          .authorizeHttpRequests(requests -> requests
              .requestMatchers("/login", "/error", "/register").permitAll())
          .formLogin(form -> form
              .loginPage("/login")
              .permitAll()
          )
          .logout(logout -> logout.logoutSuccessUrl("/login")
              .invalidateHttpSession(true)
              .deleteCookies("JSESSIONID")
              .permitAll()
          ).build();
   }

   @Bean
   @ConditionalOnProperty(value = "spring.security.enabled",
       havingValue = "false")
   SecurityFilterChain securityDisabled(HttpSecurity http) throws Exception {
      return http
          .csrf(AbstractHttpConfigurer::disable)
          .authorizeHttpRequests(requests -> requests
              .anyRequest()
              .permitAll()
          )
          .build();
   }

}