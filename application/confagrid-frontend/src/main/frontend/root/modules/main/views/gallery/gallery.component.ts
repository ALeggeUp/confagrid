/*
 * gallery.component.ts
 *
 * Copyright (C) 2017-2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Component, Inject, OnInit, AfterViewInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';

import { WordGridService } from '../../services/word-grid.service';
import { WordGridResponseItem } from '../../models/word-grid-response-item.model';

@Component({
    selector: 'app-gallery-grid',
    templateUrl: './gallery.component.html',
    styleUrls: ['./gallery.component.css']
})

export class GalleryComponent implements OnInit, AfterViewInit {

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
