/*
 * login-request.ts
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { BaseRequest } from './base-request';

export interface LoginRequest extends BaseRequest {
  name: string;
  password: string;
}
