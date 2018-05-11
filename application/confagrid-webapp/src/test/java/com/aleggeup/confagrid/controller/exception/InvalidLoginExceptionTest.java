/*
 * InvalidLoginExceptionTest.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.controller.exception;

import static org.junit.Assert.assertNotNull;

import com.aleggeup.confagrid.auth.InvalidLoginException;
import org.junit.Test;

public class InvalidLoginExceptionTest {

    @Test
    public void basicTest() {
        final InvalidLoginException invalidLoginException = new InvalidLoginException();
        assertNotNull(invalidLoginException);
    }
}
