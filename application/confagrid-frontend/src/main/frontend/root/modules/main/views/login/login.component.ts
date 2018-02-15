/*
 * login.component.ts
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';

import { AuthGuardService } from '../../services/auth-guard.service';
import { AuthenticationService } from '../../services/authentication.service';

@Component({
    selector: 'app-login-content',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})

export class LoginComponent {

    loginForm: FormGroup;

    constructor(private http: HttpClient,
            private authGuardService: AuthGuardService,
            private router: Router,
            private formBuilder: FormBuilder,
            private authService: AuthenticationService) {
        this.createForm();
    }

    createForm() {
        this.loginForm = this.formBuilder.group({
            name: '',
            password: ''
        });
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

    onSubmit() {
        this.authService
            .login(this.loginForm.value)
            .subscribe(data => {
                console.log('token: ' + data.token);
                this.authService.currentToken = data.token;
                this.toggleLogin();
            });
    }
}
