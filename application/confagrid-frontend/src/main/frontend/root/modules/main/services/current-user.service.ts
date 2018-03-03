/*
 * current-user.service.ts
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Injectable } from '@angular/core';

import { LoginResponse } from '../models/mex/login-response';
import { BrowserStorageService } from './browser-storage.service';

@Injectable()
export class CurrentUserService {

    private loginResponse: LoginResponse;

    constructor(private browserStorageService: BrowserStorageService) {
    }

    set currentUser(user: LoginResponse) {
        this.loginResponse = user;
        this.browserStorageService.setUser(user);
    }

    get currentUser(): LoginResponse {
        return this.browserStorageService.getUser();
    }
}
