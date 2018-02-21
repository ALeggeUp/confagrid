/*
 * intro.component.ts
 *
 * Copyright (C) 2017-2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Component, Inject, OnInit, AfterViewInit } from '@angular/core';
import { AuthenticationService } from '../../services/authentication.service';
import { BrowserStorageService } from '../../services/browser-storage.service';

@Component({
    selector: 'app-intro-content',
    templateUrl: './intro.component.html',
    styleUrls: ['./intro.component.css']
})

export class IntroComponent implements OnInit, AfterViewInit {
    title = 'Confagrid';
    subtitle = 'Word Grid Generator';

    constructor(private authenticationService: AuthenticationService, private browserStorageService: BrowserStorageService) {
    }

    ngOnInit() {
    }

    ngAfterViewInit() {
        if (this.browserStorageService.getString('token')) {
            this.authenticationService.currentToken = this.browserStorageService.getString('token');
        }
        this.authenticationService.check()
            .subscribe(data => console.log(data));
    }
}
