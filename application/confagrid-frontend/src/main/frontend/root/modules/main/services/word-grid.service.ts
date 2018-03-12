/*
 * word-grid.service.ts
 *
 * Copyright (C) 2017-2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Rx';

import { HttpService } from './http.service';

import { WordGridModel } from '../models/word-grid.model';

import { WordGridUpdateRequest } from '../models/mex/word-grid-update-request';
import { WordGridContentResponse } from '../models/mex/word-grid-content-response';

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

    get(id: string): Observable<WordGridModel> {
        return this.httpService.getRequest<WordGridModel>('/api/v1/word-grid/' + id);
    }

    getContent(): Observable<WordGridContentResponse> {
        return this.httpService.getRequest<WordGridContentResponse>('/api/v1/word-grid/content');
    }

    update(id: string, request: WordGridUpdateRequest): Observable<WordGridContentResponse> {
        return this.httpService.putRequest<WordGridUpdateRequest, WordGridContentResponse>('/api/v1/word-grid/' + id + '/update', request);
    }
}
