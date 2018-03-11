/*
 * word-grid.model.ts
 *
 * Copyright (C) 2017-2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

export interface WordGridModel {
  id: string;
  title: string;
  creator: {
    id: string;
    name: string;
  };
  dimensionHeight: number;
  dimensionWidth: number;
  dimensions: string;
  description: string;
}
