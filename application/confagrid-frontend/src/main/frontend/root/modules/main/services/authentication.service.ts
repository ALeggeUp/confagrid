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
import { AbstractHttpService } from './abstract-http.service';

@Injectable()
export class AuthenticationService extends AbstractHttpService {

    constructor(http: Http) {
        super(http);
    }

    login(request: LoginRequest): Observable<LoginResponse> {
        return this.postRequest<LoginRequest, LoginResponse>('/user/login', request);
    }
}
