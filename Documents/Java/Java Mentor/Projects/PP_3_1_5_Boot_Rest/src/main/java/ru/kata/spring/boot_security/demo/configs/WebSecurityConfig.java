package ru.kata.spring.boot_security.demo.configs;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

@Configuration

public class WebSecurityConfig {

  private final SuccessUserHandler successUserHandler;
  private final UserServiceImpl userService;

  public WebSecurityConfig(SuccessUserHandler successUserHandler,
      @Lazy UserServiceImpl userService) {
    this.successUserHandler = successUserHandler;
    this.userService = userService;

  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .cors().disable()
        .csrf().disable()
        .authorizeHttpRequests((auth) -> auth
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/user/**").hasAnyRole("ADMIN", "USER")
            .requestMatchers("/resources/**", "/**").permitAll()
            .anyRequest().authenticated())

        .formLogin().loginPage("/login")
        .successHandler(successUserHandler).permitAll()
        .and()
        .logout()
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login?logout")
        .permitAll();
    return http.build();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new
        UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.addAllowedMethod("*");
    source.registerCorsConfiguration("/**", config.applyPermitDefaultValues());
    return source;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    authenticationProvider.setUserDetailsService(userService);
    return authenticationProvider;
  }

  @Bean
  public JdbcUserDetailsManager users(DataSource dataSource) {
    JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
    return jdbcUserDetailsManager;
  }

}