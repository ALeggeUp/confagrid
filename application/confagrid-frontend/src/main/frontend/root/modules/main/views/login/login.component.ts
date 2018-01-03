/*
 * login.component.ts
 *
 * Copyright (C) 2017 Stephen Legge, Tim McCrabb and Andy Redfearn
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AuthGuardService } from '../../services/auth-guard.service';

@Component({
    selector: 'app-login-content',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})

export class LoginComponent {

    constructor(private authGuardService: AuthGuardService, private router: Router) {
    }

    toggleLogin() {
        console.log('toggleLogin');
        this.authGuardService.isLoggedIn = !this.authGuardService.isLoggedIn;
        console.log('logged in ' + this.authGuardService.isLoggedIn);
        if (!this.authGuardService.isLoggedIn) {
            this.router.navigate(['/login']);
        } else {
            this.router.navigate(['/intro']);
        }
    }
}
