/*
 * word-grid-content-response.ts
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { BaseResponse } from './base-response';
import { CellModel } from '../cell.model';

export interface WordGridContentResponse extends BaseResponse {
  wordGrid: {
    id: string;
    title: string;
    width: number;
    height: number;
    phrases: WordGridContentPhraseItem[];
  };
  gridWidth: number;
  gridHeight: number;
  cells: CellModel[];
}

export interface WordGridContentPhraseItem {
  id: string;
  raw: string;
  words: WordGridContentWordItem[];
}

export interface WordGridContentWordItem {
  wordId: string;
  text: string;
  sequence: number;
}
