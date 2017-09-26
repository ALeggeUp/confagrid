/*
 * contact.component.ts
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Component, Inject, OnInit, AfterViewInit } from '@angular/core';

@Component({
    selector: 'app-contact-content',
    templateUrl: './contact.component.html',
    styleUrls: ['./contact.component.less', './content.album.css']
})

export class ContactComponent implements OnInit, AfterViewInit {
    title = 'Confagrid';

    constructor() {
    }

    ngOnInit() {
    }

    ngAfterViewInit() {
    }

    scratch() {
        alert('app-component-scratch');
    }
}
