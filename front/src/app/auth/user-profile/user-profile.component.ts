import {Component, Input, OnInit} from '@angular/core';
import {SoinsModel} from '../../shared/soins-model';
import {CommentPayload} from '../../comment/comment-payload';
import {ActivatedRoute, Router} from '@angular/router';
import {PostService} from '../../shared/post.service';
import {CommentService} from '../../comment/comment.service';
import {SignupRequestPayload} from '../signup/singup-request.payload';
import {AuthService} from '../shared/auth.service';
import {Observable, throwError} from 'rxjs';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  name: string;
  posts: SoinsModel[];
  comments: CommentPayload[];
  postLength: number;
  commentLength: number;
 patient: SignupRequestPayload;

  constructor(private activatedRoute: ActivatedRoute, private postService: PostService,
              private commentService: CommentService, private authService: AuthService,
              private router: Router) {
    this.name = this.activatedRoute.snapshot.params.name;
    this.postService.getAllPostsByUser(this.name).subscribe(data => {
      this.posts = data;
      this.postLength = data.length;
    });
    this.commentService.getAllCommentsByUser(this.name).subscribe(data => {
      this.comments = data;
      this.commentLength = data.length;
    });
  }

  ngOnInit(): void {
    this.getPatientByName();
  }

  goToPost(id: number): void {
    this.router.navigateByUrl('/view-soins/' + id);
  }

  private getPatientByName() {
    this.authService.getPatientByName(this.activatedRoute.snapshot.params.name).subscribe(data => {
      this.patient = data;
    }, error => {
      throwError(error);
    });
  }

  public changStaut(commentPayload: CommentPayload): void {
    commentPayload.confirmPS = true;
    this.commentService.updateComment(commentPayload).subscribe(data => {
      this.commentService.getAllCommentsByUser(this.name);
    }, error => {
      throwError(error);
    });
  }

}
