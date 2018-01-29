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
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
    public void test() {
        Mockito.when(mockCorsRegistration.allowedOrigins(SystemConfig.CORS_ALLOWED_ORIGINS)).thenReturn(mockCorsRegistration);
        Mockito.when(mockCorsRegistry.addMapping(SystemConfig.CORS_MAPPING)).thenReturn(mockCorsRegistration);

        final WebMvcConfigurer configurer = systemConfig.corsConfigurer();
        configurer.addCorsMappings(mockCorsRegistry);

        Mockito.verify(mockCorsRegistry).addMapping(SystemConfig.CORS_MAPPING);
        Mockito.verify(mockCorsRegistration).allowedOrigins(SystemConfig.CORS_ALLOWED_ORIGINS);
    }
}
