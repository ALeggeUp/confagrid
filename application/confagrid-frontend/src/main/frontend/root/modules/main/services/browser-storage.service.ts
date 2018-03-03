/*
 * browser-storage.service.ts
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Injectable } from '@angular/core';
import { Http, Response, Headers, URLSearchParams, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { LoginResponse } from '../models/mex/login-response';

@Injectable()
export class BrowserStorageService {

    private readonly KEY_USER = 'user';

    private readonly DEFAULT_EXPIRY_HOURS = 1;
    private readonly HOUR_IN_MILLIS: number = 60 * 60 * 1000;

    protected localStorageAvailable = false;

    constructor() {
        if (localStorage) {
            this.localStorageAvailable = true;
        }
    }

    setString(key: string, value: string) {
        if (this.localStorageAvailable) {
            localStorage[key] = value;
        } else {
            this.createCookie(key, value, 1);
        }
    }

    getString(key: string) {
        if (this.localStorageAvailable) {
            return localStorage[key];
        } else {
            return document.cookie;
        }
    }

    setUser(value: LoginResponse) {
        if (this.localStorageAvailable) {
            localStorage[this.KEY_USER] = JSON.stringify(value);
        }
    }

    getUser(): LoginResponse {
        if (this.localStorageAvailable) {
            const storedKey: string = localStorage[this.KEY_USER];
            return storedKey ? JSON.parse(storedKey) : '{}';
        }
    }

    private createCookie(key: string, value: string, expiryHours: number = this.DEFAULT_EXPIRY_HOURS) {
        const date: Date = new Date();
        date.setTime(date.getTime() + (expiryHours * this.HOUR_IN_MILLIS));
        const expires: string = 'expires=' + date.toUTCString();
        document.cookie = encodeURIComponent(key) + '=' + encodeURIComponent(value) + '; ' + expires + '; path=/';
    }
}
