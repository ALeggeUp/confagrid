/*
 * main.module.ts
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MainRoutingModule } from './main-routing.module';
import { IntroComponent } from './views/intro/intro.component';
import { ContactComponent } from './views/contact/contact.component';

import { LoggerService } from './services/logger.service';

@NgModule({
  declarations: [
    IntroComponent,
    ContactComponent
  ],
  imports: [
    MainRoutingModule
  ],
  exports: [
  ],
  providers: [
    LoggerService
  ]
})

export class MainModule {
}
