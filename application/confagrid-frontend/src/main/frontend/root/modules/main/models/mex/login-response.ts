/*
 * login-response.ts
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { BaseResponse } from './base-response';

export interface LoginResponse extends BaseResponse {
  userId: string;
  userName: string;
  token: string;
}
