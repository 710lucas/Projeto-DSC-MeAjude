package com.si.meAjude.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    SecurityFilter securityFilter;

    private static final String[] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
        "/v3/api-docs/**",
        "/swagger-ui/**",
        "/swagger-ui.html"
    };


    private static final String [] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED_POST_REQUEST = {
            "/auth/login", // Url que usaremos para fazer login
            "/users" // Url que usaremos para criar um usuário
    };

    private static final String [] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED_GET_REQUEST = {
            "/donations/**",
            "/campaigns/**"
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity
                .cors(cors -> cors.configure(httpSecurity))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                        .requestMatchers(HttpMethod.GET, ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED_GET_REQUEST).permitAll()
                        .requestMatchers(HttpMethod.POST, ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED_POST_REQUEST).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*"); // Permitir qualquer origem. Ajuste conforme necessário.
        configuration.addAllowedMethod("*"); // Permitir todos os métodos HTTP.
        configuration.addAllowedHeader("*"); // Permitir todos os cabeçalhos.

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
