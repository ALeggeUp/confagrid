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

import { WordGridModel } from '../../models/word-grid.model';

@Component({
    selector: 'app-create-grid',
    templateUrl: './create.component.html',
    styleUrls: ['./create.component.css']
})

export class CreateComponent implements OnInit {

    @Input()
    wordGrid: WordGridModel;

    wordGridForm: FormGroup;

    constructor(private router: Router, private formBuilder: FormBuilder) {
        this.createForm();
    }

    createForm() {
        this.wordGridForm = this.formBuilder.group({
            title: '',
            dimensions: '',
            description: ''
        });
    }

    ngOnInit() {
    }

    onSubmit() {
        console.log('onSubmit');
        console.log(this.wordGridForm.value);
        this.router.navigate(['/create', this.wordGridForm.value.title]);
    }
}
