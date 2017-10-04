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

import { ContactComponent } from './views/contact/contact.component';
import { CreateComponent } from './views/create/create.component';
import { EditComponent } from './views/edit/edit.component';
import { ExistingComponent } from './views/existing/existing.component';
import { IntroComponent } from './views/intro/intro.component';
import { MoreComponent } from './views/more/more.component';

export const routes: Routes = [
  { path: '', redirectTo: '/intro', pathMatch: 'full' },
  { path: 'contact', component: ContactComponent },
  { path: 'create', component: CreateComponent },
  { path: 'create/:title', component: CreateComponent },
  { path: 'edit/:id', component: EditComponent },
  { path: 'existing', component: ExistingComponent },
  { path: 'intro', component: IntroComponent },
  { path: 'more', component: MoreComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class MainRoutingModule {
}
