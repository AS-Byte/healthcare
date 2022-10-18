import { Component, OnInit } from '@angular/core';
import {SoinsModel} from '../../shared/soins-model';
import {ActivatedRoute, Router} from '@angular/router';
import {PostService} from '../../shared/post.service';
import {throwError} from 'rxjs';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {CommentPayload} from '../../comment/comment-payload';
import {CommentService} from '../../comment/comment.service';
import {AuthService} from '../../auth/shared/auth.service';
import {SignupRequestPayload} from '../../auth/signup/singup-request.payload';
import {HttpClient} from '@angular/common/http';
import {LocalStorageService} from 'ngx-webstorage';

@Component({
  selector: 'app-view-soins',
  templateUrl: './view-soins.component.html',
  styleUrls: ['./view-soins.component.css']
})
export class ViewSoinsComponent implements OnInit {

  postId: number;
  post: SoinsModel;
  commentForm: FormGroup;
  commentPayload: CommentPayload;
  comments: CommentPayload[];
  patient: SignupRequestPayload;
  username = this.getUserName();



  constructor(private postService: PostService, private activatedRoute: ActivatedRoute, private commentService: CommentService, private router: Router, private authService: AuthService, private localStorage: LocalStorageService) {
    this.postId = this.activatedRoute.snapshot.params.id;
    this.commentForm = new FormGroup({
      text: new FormControl('', Validators.required),
      telPS: new FormControl('', Validators.required),
      emailPS: new FormControl('', Validators.required)
    });
    this.commentPayload = {
      text: '',
      soinsId: this.postId,
      telPS: '',
      emailPS: '',
      confirmPS: false
    };
  }

  ngOnInit(): void {
    this.getPostById();
    this.getCommentsForPost();
    this.getPatientByName();

  }


  private getPatientByName() {
    this.authService.getPatientByName(this.activatedRoute.snapshot.params.name).subscribe(data => {
      this.patient = data;
    }, error => {
      throwError(error);
    });
  }

  private getPostById() {
    this.postService.getPost(this.postId).subscribe(data => {
      this.post = data;
    }, error => {
      throwError(error);
    });
  }

  postComment() {
    this.commentPayload.text = this.commentForm.get('text').value;
    this.commentPayload.telPS = this.commentForm.get('telPS').value;
    this.commentPayload.emailPS = this.commentForm.get('emailPS').value;
    this.commentService.postComment(this.commentPayload).subscribe(data => {
      this.commentForm.get('text').setValue('');
      this.commentForm.get('telPS').setValue('');
      this.commentForm.get('emailPS').setValue('');
      this.getCommentsForPost();
    }, error => {
      throwError(error);
    });
  }

  private getCommentsForPost() {
    this.commentService.getAllCommentsForPost(this.postId).subscribe(data => {
      this.comments = data;
    }, error => {
      throwError(error);
    });
  }

  public changStaut(comment: CommentPayload): void {
    console.log(comment, 'test1');
    comment.confirmPS = true;
    this.commentService.updateComment(comment).subscribe((response: CommentPayload) => {
      console.log(comment);
    }, error => {
      throwError(error);
    });
  }

  getUserName() {
    return this.localStorage.retrieve('username');
  }


}
