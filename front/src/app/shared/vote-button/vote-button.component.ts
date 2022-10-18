import {Component, Input, OnInit} from '@angular/core';
import {SoinsModel} from '../soins-model';
import { faArrowUp, faArrowDown } from '@fortawesome/free-solid-svg-icons';
import {AuthService} from '../../auth/shared/auth.service';
import {PostService} from '../post.service';
import {ToastrService} from 'ngx-toastr';
import {throwError} from 'rxjs';
import {VoteType} from './vote-type';
import {VoteService} from '../vote.service';
import {VotePayload} from './vote-payload';


@Component({
  selector: 'app-vote-button',
  templateUrl: './vote-button.component.html',
  styleUrls: ['./vote-button.component.css']
})
export class VoteButtonComponent implements OnInit {

  @Input() post: SoinsModel;
  votePayload: VotePayload;
  faArrowUp = faArrowUp;
  faArrowDown = faArrowDown;
  upvoteColor: string;
  downvoteColor: string;

  constructor(private voteService: VoteService,
              private authService: AuthService,
              private postService: PostService, private toastr: ToastrService) {
    this.votePayload = {
      voteType: undefined,
      soinsId: undefined
    };
  }

  ngOnInit(): void {
    this.updateVoteDetails();
  }

  upvotePost() {
    this.votePayload.voteType = VoteType.UPVOTE;
    this.vote();
    this.downvoteColor = '';
  }

  downvotePost() {
    this.votePayload.voteType = VoteType.DOWNVOTE;
    this.vote();
    this.upvoteColor = '';
  }

  private vote() {
    this.votePayload.soinsId = this.post.id;
    this.voteService.vote(this.votePayload).subscribe(() => {
      this.updateVoteDetails();
    }, error => {
      this.toastr.error(error.error.message);
      throwError(error);
    });
  }

  private updateVoteDetails() {
    this.postService.getPost(this.post.id).subscribe(post => {
      this.post = post;
    });
  }

}
