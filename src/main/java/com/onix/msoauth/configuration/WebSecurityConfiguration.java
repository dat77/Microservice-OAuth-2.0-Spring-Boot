package com.onix.msoauth.configuration;

import com.onix.msoauth.repository.RoleRepository;
import com.onix.msoauth.services.SecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SecurityUserDetailsService securityUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
//                .authorizeRequests()
//                .antMatchers("/employees/**").permitAll()
//                .antMatchers("/teams/**").permitAll()
//                .antMatchers("/users/signin").permitAll()
//                .anyRequest().authenticated();
                .authorizeRequests( authReq ->
                        authReq                 // it's a new approach with lambda DSL
                            .antMatchers("/employees/**").permitAll() //to restrict the permission could be f.e. hasRole("ADMIN")
                            .antMatchers("/teams/**").permitAll() //to restrict the permission could be f.e. hasAnyRole("ADMIN", "USER")
                            .antMatchers("/users/signin").permitAll()
                            .anyRequest().authenticated())
                .addFilterBefore(new JwtTokenFilter(securityUserDetailsService),
                                 UsernamePasswordAuthenticationFilter.class);  // Basic authentication

        // Disable CSRF (cross site request forgery)
        httpSecurity.csrf().disable();
        // No session will be created or used by spring security
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


}
