/*
 * cell.ts
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { CellModel } from '../cell.model';

export class Cell implements CellModel {

    private _isSelected = false;

    character: string;
    cellHover: boolean;

    constructor(character: string) {
        this.character = character;
        this.cellHover = false;
    }

    isSelected = function(): boolean {
        return this._isSelected;
    };
}
