/*
 * metamask.service.ts
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Injectable, Output, EventEmitter } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/timer';

@Injectable()
export class MetaMaskService {

    private web3Instance : any;

    @Output() update = new EventEmitter();

    constructor() {
        Observable.timer(500, 1000).subscribe((value) => this.refresh(value));
    }

    refresh(value: number): void {
        if (!this.web3) {
            this.web3 = new this.Web3(window['web3'].currentProvider);
            this.setAccount(this.web3);
        } else {
            this.setAccount(this.web3);
        }
    }
    
    setAccount(web3: any): void {
        var account = web3.eth.accounts[0];
        if (!localStorage['lastAccount'] || localStorage['lastAccount'] !== account) {
            localStorage['lastAccount'] = account;
            console.log(account);
        }
    }

    isConnected(): boolean {
        return (typeof window['web3'] !== 'undefined');
    }
    
    get Web3(): any {
        return window['Web3'];
    }

    get web3(): any {
        return this.web3Instance;
    }

    set web3(web3: any) {
        this.web3Instance = web3;
    }
}