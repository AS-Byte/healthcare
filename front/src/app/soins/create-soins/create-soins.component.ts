import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {PostService} from '../../shared/post.service';
import {TypeSoinsService} from '../../typeSoins/type-soins.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {CreateSoinsPayload} from './create-soins.payload';
import {TypeSoinsModel} from '../../typeSoins/typeSoins-model';
import {throwError} from 'rxjs';
import {formatDate} from '@angular/common';

@Component({
  selector: 'app-create-soins',
  templateUrl: './create-soins.component.html',
  styleUrls: ['./create-soins.component.css']
})
export class CreateSoinsComponent implements OnInit {

  createPostForm: FormGroup;
  postPayload: CreateSoinsPayload;
  subreddits: Array<TypeSoinsModel>;
  typeSoins: string;

  constructor(private router: Router, private postService: PostService,
              private subredditService: TypeSoinsService) {
    console.log(this.postPayload);
    this.createPostForm = new FormGroup({
      soinsName: new FormControl('', Validators.required),
      typeSoinsName: new FormControl('', Validators.required),
      url: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),
      perfusion: new FormControl('', Validators.required),
      pansement: new FormControl('', Validators.required),
      stomie: new FormControl('', Validators.required),
      pansementMeches: new FormControl('', Validators.required),
      injection: new FormControl('', Validators.required),
      injecter: new FormControl('', Validators.required),
      journeeSoins: new FormControl('', Validators.required),
      dureeSoins: new FormControl('', Validators.required),
      adresseSoins: new FormControl('', Validators.required),
      telSoins: new FormControl('', Validators.required),
    });
    console.log(this.postPayload);
    this.postPayload = {
      soinsName: '',
      url: '',
      description: '',
      typeSoinsName: '',
      perfusion: false,
      pansement: '',
      stomie: '',
      pansementMeches: false,
      injection: '',
      injecter: '',
      journeeSoins: '',
      dureeSoins: '',
      adresseSoins: '',
      telSoins: ''
    };

  }

  ngOnInit(): void {
    console.log(this.postPayload);
    this.subredditService.getAllSubreddits().subscribe((data) => {
      this.subreddits = data;
    }, error => {
      throwError(error);
    });
  }

  createPost() {
    console.log(this.postPayload);

    this.postPayload.soinsName = this.createPostForm.get('soinsName').value;
    this.postPayload.typeSoinsName = this.createPostForm.get('typeSoinsName').value;
    this.postPayload.url = this.createPostForm.get('url').value;
    this.postPayload.description = this.createPostForm.get('description').value;
    this.postPayload.perfusion = this.createPostForm.get('perfusion').value;
    this.postPayload.pansement = this.createPostForm.get('pansement').value;
    this.postPayload.stomie = this.createPostForm.get('stomie').value;
    this.postPayload.pansementMeches = this.createPostForm.get('pansementMeches').value;
    this.postPayload.injection = this.createPostForm.get('injection').value;
    this.postPayload.injecter = this.createPostForm.get('injecter').value;
    this.postPayload.journeeSoins = this.createPostForm.get('journeeSoins').value;
    this.postPayload.dureeSoins = this.createPostForm.get('dureeSoins').value;
    this.postPayload.adresseSoins = this.createPostForm.get('adresseSoins').value;
    this.postPayload.telSoins = this.createPostForm.get('telSoins').value;
    this.postService.createPost(this.postPayload).subscribe((data) => {
          this.router.navigateByUrl('/');
        }, error => {
          throwError(error);
        });

  }

  discardPost() {
    this.router.navigateByUrl('/');
  }

}
