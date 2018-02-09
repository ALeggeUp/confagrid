/*
 * abstract-http.service.ts
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Http, Response, Headers, URLSearchParams, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

export class AbstractHttpService {

    protected baseUrl = 'http://localhost:8080';

    constructor(private http: Http) {
    }

    protected postRequest<REQ, RESP>(path: string, request: REQ): Observable<RESP> {
        return this.http.post(this.baseUrl + path, request, this.standardOptions())
            .map(this.extractData)
            .catch(this.handleError);
    }

    protected getRequest<RESP>(path: string): Observable<RESP> {
        return this.http.get(this.baseUrl + path)
            .map(this.extractData)
            .catch(this.handleError);
    }

    protected standardOptions(): RequestOptions {
        const cpHeaders = new Headers({ 'Content-Type': 'application/json' });

        return new RequestOptions({ headers: cpHeaders });
    }

    protected extractData(res: Response) {
        const body = res.json();
        return body || {};
    }

    protected handleError (error: Response | any) {
        console.error(error.message || error);
        return Observable.throw(error.status);
    }
}
