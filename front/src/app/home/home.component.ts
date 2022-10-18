import { Component, OnInit } from '@angular/core';
import {SoinsModel} from '../shared/soins-model';
import {PostService} from '../shared/post.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  posts: Array<SoinsModel> = [];

  constructor(private postService: PostService) {
    this.postService.getAllPosts().subscribe(soins => {
      this.posts = soins;
    });
  }

  ngOnInit(): void {
  }

}
