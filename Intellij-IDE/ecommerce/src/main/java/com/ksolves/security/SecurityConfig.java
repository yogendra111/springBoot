package com.ksolves.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    @Qualifier("customAuthenticationEntryPoint")
    AuthenticationEntryPoint authEntryPoint;
    @Autowired
    CustomAccessDeniedHandler customAccessDeniedHandler;
    @Autowired
    CustomAuthenticationProvider authenticationProvider;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    AuthenticationManager authenticationManager() {
        return new CustomAuthenticationManager();
    }
    @Bean
    CustomSecurityFilter customSecurityFilter(){
        return new CustomSecurityFilter(authenticationManager(), authEntryPoint);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity, @Autowired AuthenticationManager manager) throws Exception {

        httpSecurity.csrf(csrf->csrf.disable())
                .addFilterAt(customSecurityFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(request-> {
                    request.requestMatchers("/auth/**", "/welcome", "/error").permitAll();
                    request.requestMatchers("/user").hasRole("USER");
                    request.requestMatchers("/product", "/admin").hasRole("ADMIN");
                    request.requestMatchers("/store/**").permitAll();
                    request.anyRequest().authenticated();
                })
//                .httpBasic(http->{
//                    http.authenticationEntryPoint(customAuthenticationEntryPoint);
//                })
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling(exceptionHandler->{
                    exceptionHandler.authenticationEntryPoint(authEntryPoint);
                    exceptionHandler.accessDeniedHandler(customAccessDeniedHandler);    //No Default implementation to use with JWT
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        SecurityFilterChain securityFilterChain = httpSecurity.build();
//        securityFilterChain.getFilters().remove(6);
        securityFilterChain.getFilters().stream().forEach(System.out::println);
        return securityFilterChain;
    }

//    @Bean
//    AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
//        return authenticationManagerBuilder.build();
//    }

//    @Bean
//    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }

//    @Bean
//    AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }

//    @Bean
//    public UserDetailsService users() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("1234"))
//                .roles("USER")
//                .build();
//        UserDetails admin = User
//                .withUsername("admin")
//                .password(passwordEncoder().encode("12345"))
//                .roles("USER","ADMIN")
//                .build();
//
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(user);
//        manager.createUser(admin);
//        return manager;
//    }

//    @Bean
//    AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
//        AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
//        builder.inMemoryAuthentication().withUser("yogen").password("123").roles("ADMIN");
//        return builder.build();
//    }

}
