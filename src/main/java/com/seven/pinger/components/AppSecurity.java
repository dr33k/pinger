package com.seven.pinger.components;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Component
public class AppSecurity {
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With", "Access-Control-Request-Method",
                "Access-Control-Request-Headers"));
        configuration.setExposedHeaders(List.of("Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Content-Type",
                "Accept", "Jwt-Token", "Authorization", "Filename"));
        configuration.addAllowedOriginPattern("http://localhost:**");
        configuration.setAllowedMethods(List.of("GET", "PUT", "POST", "DELETE", "OPTIONS", "PATCH"));

        configurationSource.registerCorsConfiguration("/**",configuration);
        return new CorsFilter(configurationSource);
    }
}
