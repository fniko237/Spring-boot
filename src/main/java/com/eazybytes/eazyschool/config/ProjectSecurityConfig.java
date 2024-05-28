package com.eazybytes.eazyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        //permit all Request inside the Web Application
        http
            .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/dashboard").authenticated()
                    .requestMatchers("/home", "/", "").permitAll()
                    .requestMatchers("/holidays/**").permitAll()
                    .requestMatchers("/contact").permitAll()
                    .requestMatchers("/saveMsg").permitAll()
                    .requestMatchers("/courses").permitAll()
                    .requestMatchers("/about").permitAll()
                    .requestMatchers("/assets/**").permitAll()
                    .requestMatchers("/login").permitAll()
            )
            .formLogin(formLogin -> formLogin
                    .loginPage("/login") // Specify the custom login page URL
//                        .loginProcessingUrl("/home") // URL to submit the username and password to
                    .defaultSuccessUrl("/dashboard", true) // URL to redirect to after a successful login
                    .failureUrl("/login?error=true") // URL to redirect to after a failed login
                    .permitAll() // Allow all users to see the login page
            )
            .logout(logout -> logout
                    .logoutUrl("/logout") // URL to trigger logout
                    .invalidateHttpSession(true) //Invalidate Http Session
                    .deleteCookies("JSESSIONID") // Invalidate session cookies
                    .logoutSuccessUrl("/login?logout=true") // URL to redirect to after successful logout
                    .permitAll()
            )
            .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userService(){
        UserDetails user  = User.withDefaultPasswordEncoder()
                .username("dilane")
                .password("12345")
                .roles("USER")
                .build();

        UserDetails admin  = User.withDefaultPasswordEncoder()
                .username("azangue")
                .password("12345")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

}
