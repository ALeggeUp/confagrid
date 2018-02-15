/*
 * SecurityConfig.java
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.config;

import java.security.SecureRandom;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aleggeup.confagrid.filter.JwtFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());

        return registrationBean;
    }

    @Bean
    public SecureRandom secureRandom() {
        return new SecureRandom();
    }
}
