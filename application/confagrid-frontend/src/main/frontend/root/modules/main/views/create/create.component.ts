/*
 * create.component.ts
 *
 * Copyright (C) 2017 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { HttpService } from '../../services/http.service';

import { WordGridModel } from '../../models/word-grid.model';
import { WordGridResponseItem } from '../../models/word-grid-response-item.model';

@Component({
    selector: 'app-create-grid',
    templateUrl: './create.component.html',
    styleUrls: ['./create.component.css']
})

export class CreateComponent implements OnInit {

    @Input()
    wordGrid: WordGridModel;

    wordGridForm: FormGroup;

    constructor(private httpService: HttpService, private router: Router, private formBuilder: FormBuilder) {
        this.createForm();
    }

    createForm() {
        this.wordGridForm = this.formBuilder.group({
            title: '',
            dimensions: '',
            dimensionWidth: -1,
            dimensionHeight: -1,
            description: ''
        });
    }

    ngOnInit() {
    }

    onSubmit() {
        const wordGrid = this.wordGridForm.value;
        const dimensions = wordGrid['dimensions'].split('x');
        wordGrid['dimensionWidth'] = dimensions[0];
        wordGrid['dimensionHeight'] = dimensions[1];
        this.httpService
            .postRequest<WordGridModel, WordGridResponseItem[]>('/api/v1/word-grids', this.wordGridForm.value)
            .subscribe(data => {
                this.router.navigate(['/edit'], data[0]['id']);
            });
    }
}
