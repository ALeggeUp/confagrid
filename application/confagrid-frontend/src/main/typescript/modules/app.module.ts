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

import { ContentComponent } from './content/content.component';
import { CoreModule } from './core/core.module';

import { CollarDirective } from './core/directive/collar.directive';

@NgModule({
  declarations: [
    ContentComponent,
    CollarDirective
  ],
  imports: [
    BrowserModule,
    CoreModule
  ],
  providers: [],
  bootstrap: [ContentComponent]
})

export class AppModule {
}
