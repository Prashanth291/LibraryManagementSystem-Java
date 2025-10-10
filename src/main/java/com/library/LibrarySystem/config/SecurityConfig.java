package com.library.LibrarySystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/h2-console/**", "/css/**", "/js/**").permitAll()

                .requestMatchers("/books/add", "/books/save", "/books/edit/**", "/books/update", "/books/delete/**").hasRole("ADMIN")
                .requestMatchers("/members/add", "/members/save", "/members/edit/**", "/members/update", "/members/delete/**").hasRole("ADMIN")

                .requestMatchers("/", "/books", "/members", "/transactions", "/transactions/**").hasAnyRole("ADMIN", "LIBRARIAN")

                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername("admin")
            .password("{noop}admin123") 
            .roles("ADMIN")
            .build();

        UserDetails librarian = User.withUsername("librarian")
            .password("{noop}lib123")
            .roles("LIBRARIAN")
            .build();
            
        return new InMemoryUserDetailsManager(admin, librarian);
    }
}