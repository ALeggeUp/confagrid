/*
 * filedrop.component.ts
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'file-drop',
  templateUrl: './filedrop.component.html',
  styles: [`
    .custom-component-drop-zone {
      width: 300px;
      height: 300px;
    }

    #filedrag {
      display: block;
      font-weight: bold;
      text-align: center;
      padding: 1em 0;
      margin: 1em 0;
      color: #555;
      border: 2px dashed #555;
      border-radius: 7px;
      cursor: default;
    }

    #filedrag.hover {
      color: #f00;
      border-color: #f00;
      border-style: solid;
      box-shadow: inset 0 3px 4px #888;
    }
  `]
})

export class FileDropComponent implements OnInit {

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
