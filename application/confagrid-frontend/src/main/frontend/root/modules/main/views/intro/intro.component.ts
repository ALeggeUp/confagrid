/*
 * intro.component.ts
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Component, Inject, OnInit, AfterViewInit } from '@angular/core';

@Component({
    selector: 'app-intro-content',
    templateUrl: './intro.component.html',
    styleUrls: ['./intro.component.css']
})

export class IntroComponent implements OnInit, AfterViewInit {
    title = 'Confagrid';
    subtitle = 'Word Grid Generator';

    constructor() {
    }

    ngOnInit() {
    }

    ngAfterViewInit() {
    }
}
