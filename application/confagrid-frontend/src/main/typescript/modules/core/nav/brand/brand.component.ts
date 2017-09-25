/*
 * brand.component.ts
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav-brand',
  templateUrl: './brand.component.html',
  styleUrls: ['./brand.component.less'],
})

export class NavBrandComponent implements OnInit {

  name = '[ A Legge Up ]';
  url = 'http://www.aleggeup.com';

  ngOnInit() {
  }

  constructor() {
  }
}
