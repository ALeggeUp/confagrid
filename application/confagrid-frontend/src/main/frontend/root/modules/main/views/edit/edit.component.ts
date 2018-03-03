/*
 * edit.component.ts
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Component, Inject, OnInit, AfterViewInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/mergeMap';

import { WordGridModel } from '../../models/word-grid.model';
import { WordGridService } from '../../services/word-grid.service';

@Component({
    selector: 'app-edit-grid',
    templateUrl: './edit.component.html',
    styleUrls: ['./edit.component.css']
})

export class EditComponent implements OnInit, AfterViewInit {

    wordGridObservable: Observable<WordGridModel>;

    constructor(private route: ActivatedRoute, private wordGridService: WordGridService) {
    }

    ngOnInit() {
        this.wordGridObservable = this.route.params.flatMap(params => {
            return this.wordGridService.get(params['id']);
        });
    }

    ngAfterViewInit() {
    }
}
