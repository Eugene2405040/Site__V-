//package app.configuration;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//@RequiredArgsConstructor
//public class SecurityConfiguration {
//
//    @Bean
//    @ConditionalOnProperty(prefix = "app.security", name = "type", value = "inMemory")
//    public PasswordEncoder inMemoryPasswordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Bean
//    @ConditionalOnProperty(prefix = "app.security", name = "type", value = "inMemory")
//    public UserDetailsService inMemoryUserDetailsService(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//
//        manager.createUser(User.withUsername("user")
//                .password("user")
//                .roles("USER")
//                .build());
//
//        manager.createUser(User.withUsername("admin")
//                .password("admin")
//                .roles("USER", "ADMIN")
//                .build());
//
//        return manager;
//    }
//
//    @Bean
//    @ConditionalOnProperty(prefix = "app.security", name = "type", value = "inMemory")
//    public AuthenticationManager inMemoryAuthentication(HttpSecurity httpSecurity
//            , UserDetailsService inMemoryUserDetailsService) throws Exception {
//
//        var authManagerBuilder = httpSecurity
//                .getSharedObject(AuthenticationManagerBuilder.class);
//
//        authManagerBuilder.userDetailsService(inMemoryUserDetailsService);
//
//        return authManagerBuilder.build();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity
//            , AuthenticationManager authenticationManager) throws Exception {
//        httpSecurity.authorizeHttpRequests(auth -> auth.requestMatchers("/", "/**")
//                .hasAnyRole("USER", "ADMIN")
//                .requestMatchers("/admin/create", "/admin/delete/**", "admin/update/**")
//                .hasRole("ADMIN")
//                .requestMatchers("/", "/**").permitAll()
//                .anyRequest().authenticated())
//                .csrf(AbstractHttpConfigurer::disable)
//                .httpBasic(Customizer.withDefaults())
//                .sessionManagement(httpSecuritySessionManagementConfigurer
//                        -> httpSecuritySessionManagementConfigurer
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authenticationManager(authenticationManager);
//
//        return httpSecurity.build();
//    }
//}
