/*
 * app.module.ts
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';

import { MainModule } from '../main/main.module';
import { SharedModule } from '../shared/shared.module';

import { AppRoutingModule } from './app-routing.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    MainModule,
    SharedModule,
    AppRoutingModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}
