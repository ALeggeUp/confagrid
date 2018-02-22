/*
 * base-request.ts
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

export interface BaseRequest {
    additionalProperties: Map<string, string>;
}