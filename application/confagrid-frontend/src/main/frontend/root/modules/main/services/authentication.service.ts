/*
 * authentication.service.ts
 *
 * Copyright (C) 2017-2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Injectable } from '@angular/core';
import { Http, Response, Headers, URLSearchParams, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

import { LoginRequest } from '../models/mex/login-request';
import { LoginResponse } from '../models/mex/login-response';

import { HttpService } from './http.service';

@Injectable()
export class AuthenticationService {

    constructor(private httpService: HttpService) {
    }

    check(): Observable<LoginResponse> {
        return this.httpService.getRequest('/user/check');
    }

    login(request: LoginRequest): Observable<LoginResponse> {
        return this.httpService.postRequest<LoginRequest, LoginResponse>('/user/login', request);
    }
}
