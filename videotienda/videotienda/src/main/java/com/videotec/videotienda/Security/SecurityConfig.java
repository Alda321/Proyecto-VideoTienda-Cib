package com.videotec.videotienda.Security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Usuarios
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin").password("{noop}admin").roles("ADMIN").build(),
                User.withUsername("invitado").password("{noop}invitado").roles("INVITADO").build()
        );
    }

    // Manejador de acceso
    @Bean
    public AccessDeniedHandler restAccessDeniedHandler() {
        return new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response,
                               org.springframework.security.access.AccessDeniedException accessDeniedException)
                    throws IOException, ServletException {

                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Acceso denegado: no tiene permisos para realizar esta acción.\"}");
            }
        };
    }

    // Configuración del filtro de seguridad con roles y métodos HTTP
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Invitado puede solo GET y POST
                        .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "INVITADO")
                        .requestMatchers(HttpMethod.POST, "/api/**").hasAnyRole("ADMIN", "INVITADO")

                        // Admin puede todo (PUT, DELETE, etc)
                        .requestMatchers("/api/**").hasRole("ADMIN")

                        // Cualquier otra petición autenticada
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler(restAccessDeniedHandler())
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
