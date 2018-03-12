/*
 * main.module.ts
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';

import { MainRoutingModule } from './main-routing.module';
import { SharedModule } from '../shared/shared.module';

import { LoginComponent } from './views/login/login.component';
import { ContactComponent } from './views/contact/contact.component';
import { CreateComponent } from './views/create/create.component';
import { EditComponent } from './views/edit/edit.component';
import { GalleryComponent } from './views/gallery/gallery.component';
import { HomeComponent } from './views/home/home.component';
import { MoreComponent } from './views/more/more.component';
import { ViewComponent } from './views/view/view.component';

import { AuthenticationService } from './services/authentication.service';
import { AuthGuardService } from './services/auth-guard.service';
import { BrowserStorageService } from './services/browser-storage.service';
import { CurrentUserService } from './services/current-user.service';
import { HttpService } from './services/http.service';
import { LoggerService } from './services/logger.service';
import { PhraseService } from './services/phrase.service';
import { WordGridService } from './services/word-grid.service';

@NgModule({
  declarations: [
    LoginComponent,
    ContactComponent,
    CreateComponent,
    EditComponent,
    GalleryComponent,
    HomeComponent,
    MoreComponent,
    ViewComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MainRoutingModule,
    SharedModule
  ],
  exports: [
  ],
  providers: [
    AuthenticationService,
    AuthGuardService,
    BrowserStorageService,
    CurrentUserService,
    HttpService,
    LoggerService,
    PhraseService,
    WordGridService
  ]
})

export class MainModule {
}
