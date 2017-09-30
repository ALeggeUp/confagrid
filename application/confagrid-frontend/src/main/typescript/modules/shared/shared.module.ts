/*
 * shared.module.ts
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';

import { NavBarComponent } from './components/navbar/navbar.component';
import { NavBarBrandComponent } from './components/navbar/brand/brand.component';

@NgModule({
  declarations: [
    NavBarComponent,
    NavBarBrandComponent
  ],
  imports: [
    RouterModule,
    CommonModule
  ],
  exports: [
    NavBarComponent,
    NavBarBrandComponent
  ],
  providers: []
})

export class SharedModule {
}
