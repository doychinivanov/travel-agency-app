package com.travelapp.config;

import com.travelapp.models.enums.UserRoleEnum;
import com.travelapp.repositories.UserRepository;
import com.travelapp.service.AppUserDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ApplicationConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public ModelMapper configModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/", "/trip/{id}").permitAll()
                .antMatchers("/auth/login", "/auth/signup").anonymous()
                .antMatchers("/booking/payment", "/booking/trip/{id}", "/create-payment-intent").authenticated()
                .antMatchers("/admin/dashboard").hasRole(UserRoleEnum.ADMIN.name())
                .antMatchers("/trip/create/new", "/trip/edit/{id}", "/trip/delete/{id}").hasAnyRole(UserRoleEnum.ADMIN.name(), UserRoleEnum.MODERATOR.name())
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                .defaultSuccessUrl("/")
                .failureForwardUrl("/auth/login-error")
                .and()
                .logout()
                .logoutUrl("/auth/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID").and().csrf().disable();

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new AppUserDetailsService(userRepository);
    }
}
