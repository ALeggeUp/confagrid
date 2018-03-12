/*
 * word-grid-update-request.ts
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { BaseRequest } from './base-request';

export interface WordGridUpdateRequest extends BaseRequest {
  id: string;
  phrase: string;
}
