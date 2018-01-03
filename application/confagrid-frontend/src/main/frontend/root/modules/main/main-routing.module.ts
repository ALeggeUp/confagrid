/*
 * main-routing.module.ts
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './views/login/login.component';
import { ContactComponent } from './views/contact/contact.component';
import { CreateComponent } from './views/create/create.component';
import { EditComponent } from './views/edit/edit.component';
import { ExistingComponent } from './views/existing/existing.component';
import { IntroComponent } from './views/intro/intro.component';
import { MoreComponent } from './views/more/more.component';

import { AuthGuardService } from './services/auth-guard.service';

export const routes: Routes = [
  { path: '', redirectTo: '/intro', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'contact', component: ContactComponent, canActivate: [AuthGuardService] },
  { path: 'create', component: CreateComponent, canActivate: [AuthGuardService] },
  { path: 'create/:title', component: CreateComponent, canActivate: [AuthGuardService] },
  { path: 'edit/:id', component: EditComponent, canActivate: [AuthGuardService] },
  { path: 'existing', component: ExistingComponent, canActivate: [AuthGuardService] },
  { path: 'intro', component: IntroComponent, canActivate: [AuthGuardService] },
  { path: 'more', component: MoreComponent, canActivate: [AuthGuardService] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class MainRoutingModule {
}
