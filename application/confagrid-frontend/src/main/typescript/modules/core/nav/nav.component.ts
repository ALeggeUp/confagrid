/*
 * nav.component.ts
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.less'],
})

export class NavComponent implements OnInit {

  menuItems = [
    'Item 1',
    'Item 2',
    'Item 3'
  ];

  ngOnInit() {
  }

  constructor() {
  }
}
