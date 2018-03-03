/*
 * current-user.service.ts
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Injectable } from '@angular/core';

import { BrowserStorageService } from './browser-storage.service';

@Injectable()
export class CurrentUserService {

    private _currentToken: string;

    constructor(private browserStorageService: BrowserStorageService) {
    }

    set currentToken(token: string) {
        this._currentToken = token;
        this.browserStorageService.setString('token', token);
    }

    get currentToken(): string {
        return this._currentToken;
    }
}
