/*
 * logger.service.ts
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Injectable } from '@angular/core';

@Injectable()
export class LoggerService {

  log(msg: string) {
    console.log(msg);
  }

  error(msg: string) {
    console.error(msg);
  }
}
