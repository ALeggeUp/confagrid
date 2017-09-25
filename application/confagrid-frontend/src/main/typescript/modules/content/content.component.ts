/*
 * content.component.ts
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Component, Inject, OnInit, AfterViewInit } from '@angular/core';
import { MetaMaskService } from '../core/services/metamask.service';

@Component({
    selector: 'content-root',
    templateUrl: './content.component.html',
    styleUrls: ['./content.component.less', './content.album.css']
})

export class ContentComponent implements OnInit, AfterViewInit {
    title = 'Confagrid';

    constructor(@Inject(MetaMaskService) private metaMaskService) {
    }

    ngOnInit() {
    }

    ngAfterViewInit() {
    }

    scratch() {
        alert('app-component-scratch');
        console.log(this.metaMaskService.isConnected());
    }
}
