/*
 * collar.directive.ts
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Directive, HostListener, Output, EventEmitter } from '@angular/core';

@Directive({
    selector: '[appCollar]'
})

export class CollarDirective {

    @Output() itch: EventEmitter<any> = new EventEmitter();

    @HostListener('click') onClick() {
        this.itch.emit('itch itch itch');
    }

    constructor() {
    }
}
