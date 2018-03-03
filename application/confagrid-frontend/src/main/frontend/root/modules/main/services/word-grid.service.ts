/*
 * word-grid.service.ts
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

import { HttpService } from './http.service';

import { WordGridModel } from '../models/word-grid.model';
import { WordGridResponseItem } from '../models/word-grid-response-item.model';

@Injectable()
export class WordGridService {

    constructor(private httpService: HttpService) {
    }

    create(request: WordGridModel): Observable<WordGridModel[]> {
        return this.httpService.postRequest<WordGridModel, WordGridModel[]>('/api/v1/word-grids', request);
    }

    wordGrids(): Observable<WordGridModel[]> {
        return this.httpService.getRequest<WordGridModel[]>('/api/v1/word-grids');
    }
}
