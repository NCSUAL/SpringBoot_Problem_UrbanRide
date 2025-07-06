package lsh.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import lsh.security.config.filter.JwtLoginFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(t -> t.disable())
                .authorizeHttpRequests(t -> t.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())

                .addFilterAt(new JwtLoginFilter(authenticationManager(authenticationConfiguration)), UsernamePasswordAuthenticationFilter.class)

                .sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
    
    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        UserDetails userDetails = User.withUsername("user")
        .password("{noop}1111")
        .roles("USER")
        .build();

        return new InMemoryUserDetailsManager(userDetails);
    }
}
