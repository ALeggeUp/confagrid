/*
 * auth-guard.service.ts
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Injectable } from '@angular/core';
import { CanActivate, Router, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';

import { CurrentUserService } from '../services/current-user.service';

@Injectable()
export class AuthGuardService implements CanActivate {

    isLoggedIn = false;

    constructor(private router: Router, private currentUserService: CurrentUserService) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        if (this.currentUserService.currentUser && this.currentUserService.currentUser.userName !== 'anonymous') {
            return true;
        } else {
            this.router.navigate(['/login']);
            return false;
        }
    }
}
