/*
 * SystemConfig.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.aleggeup.confagrid.filter.JwtFilter;

@Configuration
public class SystemConfig {

    protected static final String CORS_MAPPING = "/**";
    protected static final String CORS_ALLOWED_ORIGINS = "http://localhost:4200";

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(final CorsRegistry registry) {
                registry.addMapping(CORS_MAPPING)
                    .allowedOrigins(CORS_ALLOWED_ORIGINS)
                    .allowedHeaders("*")
                    .exposedHeaders(JwtFilter.HEADER_CLAIMS, JwtFilter.HEADER_CLAIMS_SUBJECT);
            }
        };
    }
}
