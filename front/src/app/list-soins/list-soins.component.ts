import { Component, OnInit } from '@angular/core';
import {SoinsModel} from '../shared/soins-model';
import {PostService} from '../shared/post.service';

@Component({
  selector: 'app-list-soins',
  templateUrl: './list-soins.component.html',
  styleUrls: ['./list-soins.component.css']
})
export class ListSoinsComponent implements OnInit {

  posts: Array<SoinsModel> = [];

  constructor(private postService: PostService) {
    this.postService.getAllPosts().subscribe(soins => {
      this.posts = soins;
    });
  }

  ngOnInit(): void {
  }

}
