/*
 * word-grid.service.ts
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Injectable } from '@angular/core';
import { Http, Response, Headers, URLSearchParams, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

import { AbstractHttpService } from './abstract-http.service';
import { WordGridResponseItem } from '../models/word-grid-response.model';

@Injectable()
export class WordGridService extends AbstractHttpService {

    constructor(http:Http) {
        super(http);
    }

    wordGrids(): Observable<WordGridResponseItem[]> {
        return this.getRequest<WordGridResponseItem[]>("/api/v1/word-grids");
    }
}
