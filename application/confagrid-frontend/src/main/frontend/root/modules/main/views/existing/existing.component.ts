/*
 * existing.component.ts
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Component, Inject, OnInit, AfterViewInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';

import { WordGridService } from '../../services/word-grid.service';
import { WordGridResponseItem } from '../../models/word-grid-response-item.model';

@Component({
    selector: 'app-existing-grid',
    templateUrl: './existing.component.html',
    styleUrls: ['./existing.component.css']
})

export class ExistingComponent implements OnInit, AfterViewInit {

    private results: Observable<WordGridResponseItem[]>;

    constructor(private wordGridService: WordGridService) {
    }

    ngOnInit() {
    }

    ngAfterViewInit() {
        console.log('ngAfterViewInit');
        this.results = this.wordGridService.wordGrids();
    }
}
