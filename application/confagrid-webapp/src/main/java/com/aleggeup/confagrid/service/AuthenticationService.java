/*
 * AuthenticationService.java
 *
 * Copyright (C) 2017-2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.service;

public interface AuthenticationService {

    boolean userPasswordCheck(String username, String password);

    String authenticationToken(String username, String password);

    String anonymousToken();
}
