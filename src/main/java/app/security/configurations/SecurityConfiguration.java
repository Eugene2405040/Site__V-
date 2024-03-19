package app.security.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {


    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        // ensure the passwords are encoded properly
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(users.username("user").password("password").roles("USER").build());
        manager.createUser(users.username("admin").password("admin").roles("ADMIN").build());
        return manager;
    }


    @Bean
    public SecurityFilterChain adminLoginFilterChain(HttpSecurity http) throws Exception {
        http
//                .securityMatcher("/admin/**")
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers(HttpMethod.POST).authenticated()
//                                .requestMatchers(HttpMethod.POST).permitAll()
                                .requestMatchers(HttpMethod.DELETE).authenticated()
                                .requestMatchers(HttpMethod.PUT).authenticated()
                                .requestMatchers(HttpMethod.PATCH).authenticated()
                                .requestMatchers(HttpMethod.GET).permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(withDefaults());
        return http.build();
    }


}
