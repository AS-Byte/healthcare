import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {TypeSoinsModel} from '../typeSoins-model';
import {Router} from '@angular/router';
import {TypeSoinsService} from '../type-soins.service';

@Component({
  selector: 'app-create-type-soins',
  templateUrl: './create-type-soins.component.html',
  styleUrls: ['./create-type-soins.component.css']
})
export class CreateTypeSoinsComponent implements OnInit {
  createSubredditForm: FormGroup;
  subredditModel: TypeSoinsModel;
  title = new FormControl('');
  description = new FormControl('');


  constructor(private router: Router, private subredditService: TypeSoinsService) {
    this.createSubredditForm = new FormGroup({
      title: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required)
    });
    this.subredditModel = {
      name: '',
      description: ''
    };
  }

  ngOnInit(): void {
  }

  createSubreddit() {
    this.subredditModel.name = this.createSubredditForm.get('title').value;
    this.subredditModel.description = this.createSubredditForm.get('description').value;
    this.subredditService.createTypeSoins(this.subredditModel).subscribe(data => {
      this.router.navigateByUrl('/list-typeSoinss');
    }, error => {
      console.log('Error occurred');
    });
  }

  discard() {
    this.router.navigateByUrl('/');
  }
}
