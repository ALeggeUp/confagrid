/*
 * RouteControllerTest.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RouteControllerTest {

    @Test
    public void testRouteController() {
        final RouteController routeController = new RouteController();
        assertEquals(RouteController.DEFAULT_ROUTE, routeController.router());
    }
}
