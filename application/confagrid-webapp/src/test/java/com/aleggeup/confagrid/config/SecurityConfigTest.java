/*
 * SecurityConfigTest.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.config;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SecurityConfigTest {

    private SecurityConfig securityConfig;

    @Before
    public void setUp() {
        securityConfig = new SecurityConfig();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test() {
        assertNotNull(securityConfig.secureRandom());
        assertNotNull(securityConfig.jwtFilter());
    }
}
