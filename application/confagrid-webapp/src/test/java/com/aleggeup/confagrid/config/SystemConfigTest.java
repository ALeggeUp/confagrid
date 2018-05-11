/*
 * DatasourceConfigTest.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.config;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.aleggeup.confagrid.auth.JwtFilter;

@RunWith(MockitoJUnitRunner.class)
public class SystemConfigTest {

    @Mock
    private CorsRegistry mockCorsRegistry;

    @Mock
    private CorsRegistration mockCorsRegistration;

    private SystemConfig systemConfig;

    @Before
    public void setUp() {
        systemConfig = new SystemConfig();
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(mockCorsRegistry, mockCorsRegistration);
    }

    @Test
    public void testCorsRegistration() {
        Mockito.when(mockCorsRegistration.allowedOrigins(SystemConfig.CORS_ALLOWED_ORIGINS))
            .thenReturn(mockCorsRegistration);
        Mockito.when(mockCorsRegistry.addMapping(SystemConfig.CORS_MAPPING)).thenReturn(mockCorsRegistration);
        Mockito
            .when(mockCorsRegistration
                .allowedMethods(RequestMethod.GET.name(), RequestMethod.POST.name(), RequestMethod.PUT.name()))
            .thenReturn(mockCorsRegistration);
        Mockito.when(mockCorsRegistration.allowedHeaders("*")).thenReturn(mockCorsRegistration);
        Mockito.when(mockCorsRegistration.exposedHeaders(JwtFilter.HEADER_CLAIMS, JwtFilter.HEADER_CLAIMS_SUBJECT))
            .thenReturn(mockCorsRegistration);

        final WebMvcConfigurer configurer = systemConfig.corsConfigurer();
        configurer.addCorsMappings(mockCorsRegistry);

        Mockito.verify(mockCorsRegistry).addMapping(SystemConfig.CORS_MAPPING);
        Mockito.verify(mockCorsRegistration).allowedOrigins(SystemConfig.CORS_ALLOWED_ORIGINS);
        Mockito.verify(mockCorsRegistration)
            .allowedMethods(RequestMethod.GET.name(), RequestMethod.POST.name(), RequestMethod.PUT.name());
        Mockito.verify(mockCorsRegistration).allowedHeaders("*");
        Mockito.verify(mockCorsRegistration).exposedHeaders(JwtFilter.HEADER_CLAIMS, JwtFilter.HEADER_CLAIMS_SUBJECT);
    }
}
