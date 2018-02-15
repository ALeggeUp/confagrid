/*
 * authentication.service.ts
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Injectable } from '@angular/core';
import { Http, Response, Headers, URLSearchParams, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

import { LoginRequest } from '../models/login-request.model';
import { LoginResponse } from '../models/login-response.model';

import { BrowserStorageService } from './browser-storage.service';
import { HttpService } from './http.service';

@Injectable()
export class AuthenticationService {

    private _currentToken: string;

    constructor(private httpService: HttpService, private browserStorageService: BrowserStorageService) {
    }

    set currentToken(token: string) {
        this.httpService.currentToken = token;
        this._currentToken = token;
        this.browserStorageService.setString('token', token);
    }

    get currentToken(): string {
        return this._currentToken;
    }

    check(): Observable<LoginResponse> {
        return this.httpService.getRequest('/user/check');
    }

    login(request: LoginRequest): Observable<LoginResponse> {
        return this.httpService.postRequest<LoginRequest, LoginResponse>('/user/login', request);
    }
}
