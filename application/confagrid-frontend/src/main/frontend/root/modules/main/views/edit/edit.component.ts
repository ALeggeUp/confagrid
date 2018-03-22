/*
 * edit.component.ts
 *
 * Copyright (C) 2017-2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Component, Inject, OnInit, AfterViewInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/mergeMap';

import { CellModel } from '../../models/cell.model';
import { WordGridModel } from '../../models/word-grid.model';

import { PhraseService } from '../../services/phrase.service';
import { WordGridService } from '../../services/word-grid.service';
import { Cell } from '../../models/impl/Cell';
import { WordGridContentResponse } from '../../models/mex/word-grid-content-response';

@Component({
    selector: 'app-edit-grid',
    templateUrl: './edit.component.html',
    styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit, AfterViewInit {

    id: string;
    wordGridObservable: Observable<WordGridModel>;
    wordGridContentResponse: WordGridContentResponse;
    cells: CellModel[] = Array<Cell>();
    width: number;
    height: number;
    newItem = '';
    phrases: string[];

    constructor(private route: ActivatedRoute, private wordGridService: WordGridService, private phraseService: PhraseService) {
        this.width = 0;
        this.height = 0;
        this.newItem = '';
        this.phrases = [];
    }

    ngOnInit() {
        this.wordGridObservable = this.route.params
            .map(params => params['id'])
            .filter(id => id)
            .flatMap(id => this.wordGridService.get(id));

        this.route.params
            .map(params => params['id'])
            .filter(id => id)
            .flatMap((id) => {
                this.id = id;

                return this.wordGridService.getContent(id);
            })
            .subscribe((content) => this.transform(content));
    }

    transform(value: WordGridContentResponse) {
        this.width = value.gridWidth;
        const array: Cell[] = [];
        for (let i = 0; i < value.gridWidth * value.gridHeight; ++i) {
            this.cells.push(new Cell(' '));
        }
        this.cells = Object.assign(this.cells, array);
        value.wordGrid.phrases.forEach(phrase => this.phrases.push(phrase.raw));
    }

    ngAfterViewInit() {
    }

    trackBy(index, item) {
        return index;
    }

    mouseenter(i: number) {
        (<Cell>this.cells[i]).cellHover = true;
    }

    mouseleave(i: number) {
        (<Cell>this.cells[i]).cellHover = false;
    }

    wrap(i: number): boolean {
        return i % this.width === 0;
    }

    addItem() {
        console.log(this.newItem);
        if (this.newItem.trim() !== '') {
            this.phraseService.create({
                phrase: this.newItem.toLocaleUpperCase(),
                additionalProperties: undefined
            }).flatMap((response) => {
                this.phrases.push(response.raw);
                return this.wordGridService.update(this.id, {
                    id: response.id,
                    phrase: response.id,
                    additionalProperties: undefined
                });
            }).subscribe((response) => {
                this.newItem = '';
            });
        }
    }
}
