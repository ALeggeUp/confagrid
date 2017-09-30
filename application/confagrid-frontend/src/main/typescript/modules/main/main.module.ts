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
import { SharedModule } from '../shared/shared.module';

import { ContactComponent } from './views/contact/contact.component';
import { CreateComponent } from './views/create/create.component';
import { EditComponent } from './views/edit/edit.component';
import { ExistingComponent } from './views/existing/existing.component';
import { IntroComponent } from './views/intro/intro.component';
import { MoreComponent } from './views/more/more.component';

import { LoggerService } from './services/logger.service';

@NgModule({
  declarations: [
    ContactComponent,
    CreateComponent,
    EditComponent,
    ExistingComponent,
    IntroComponent,
    MoreComponent
  ],
  imports: [
    MainRoutingModule,
    SharedModule
  ],
  exports: [
  ],
  providers: [
    LoggerService
  ]
})

export class MainModule {
}
