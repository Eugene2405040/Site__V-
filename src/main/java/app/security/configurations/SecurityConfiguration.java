package app.security.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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

    //    @Bean
//    SecurityFilterChain springWebFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .httpBasic(AbstractHttpConfigurer::disable)
//                .csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .exceptionHandling(c -> c.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/auth/signin").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/vehicles/**").permitAll()
//                        .requestMatchers(HttpMethod.DELETE, "/vehicles/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/v1/vehicles/**").permitAll()
//                        .anyRequest().authenticated()
//                )
////                .addFilterBefore(new JwtTokenAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }
    {
    }
//    @Bean
//    @Order(1)
//    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
//        http
//                .securityMatcher("/admin/**")
////                .httpBasic(AbstractHttpConfigurer::disable)
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(HttpMethod.GET, "/admin/**")
//                        .hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/admin/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PATCH, "/admin/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/admin/**").hasRole("ADMIN")
//                )
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(withDefaults())
//                .logout(Customizer.withDefaults())
//        ;
//        return http.build();
//    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().authenticated()
//                        .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                            public <O extends FilterSecurityInterceptor> O postProcess(
//                                    O fsi) {
//                                fsi.setPublishAuthorizationSuccess(true);
//                                return fsi;
//                            }
//                        })
//                );
//        return http.build();
//    }

    {
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
