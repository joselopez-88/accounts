package com.eazybites.accounts.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider") // Habilita JPA Auditing
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        // Proporciona el usuario actual (usando Spring Security)
        // return () -> Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
        //         .map(authentication -> authentication.getName());
        
        // Proporciona un auditor fijo - solo para pruebas
        return () -> Optional.of("SISTEMA");

    }
}
