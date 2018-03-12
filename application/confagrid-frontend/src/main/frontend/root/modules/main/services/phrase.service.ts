/*
 * phrase.service.ts
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Rx';

import { HttpService } from './http.service';

import { PhraseModel } from '../models/phrase.model';
import { PhraseCreateRequest } from '../models/mex/phrase-create-request';

@Injectable()
export class PhraseService {

    constructor(private httpService: HttpService) {
    }

    create(request: PhraseCreateRequest): Observable<PhraseModel> {
        return this.httpService.putRequest<PhraseCreateRequest, PhraseModel>('/api/v1/phrases/create', request);
    }
}
