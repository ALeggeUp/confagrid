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

import { CurrentUserService } from '../../services/current-user.service';
import { WordGridService } from '../../services/word-grid.service';
import { WordGridModel } from '../../models/word-grid.model';

@Component({
    selector: 'app-gallery-grid',
    templateUrl: './gallery.component.html',
    styleUrls: ['./gallery.component.css']
})

export class GalleryComponent implements OnInit, AfterViewInit {

    private results: Observable<WordGridModel[]>;

    constructor(private wordGridService: WordGridService, private currentUserService: CurrentUserService) {
    }

    isCurrentUser(item: WordGridModel): boolean {
        return item.creator.id === this.currentUserService.currentUser.userId;
    }

    ngOnInit() {
    }

    ngAfterViewInit() {
        console.log('ngAfterViewInit');
        this.results = this.wordGridService.wordGrids();
    }
}
