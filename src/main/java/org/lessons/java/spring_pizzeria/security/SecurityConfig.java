package org.lessons.java.spring_pizzeria.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    @SuppressWarnings("removal")
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        
         http.authorizeHttpRequests(requests -> requests
        .requestMatchers("/pizzas/create", "/pizzas/edit/**").hasAuthority("USER")
        .requestMatchers(HttpMethod.POST, "/pizzas/**").hasAnyAuthority("ADMIN")
        .requestMatchers("/pizzas", "/pizzas/**").hasAnyAuthority("ADMIN", "USER")
        .requestMatchers("/ingredients", "/ingredients/**").hasAuthority("ADMIN")
        .requestMatchers("/deals", "/deals/**").hasAuthority("ADMIN")
        .requestMatchers("/").permitAll()
        .requestMatchers("/**").permitAll())
        .formLogin(Customizer.withDefaults());
        
        
        
        return http.build();
    }

    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider(){
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        

      authProvider.setUserDetailsService(userDetailService());
       
        authProvider.setPasswordEncoder(passwordEncoder());


        return authProvider;
    }

    @Bean
    DatabaseUserDetailService userDetailService(){
        return new DatabaseUserDetailService();
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
