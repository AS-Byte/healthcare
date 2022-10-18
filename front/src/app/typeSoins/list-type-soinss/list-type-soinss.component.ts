import { Component, OnInit } from '@angular/core';
import {TypeSoinsService} from '../type-soins.service';
import {throwError} from 'rxjs';
import {TypeSoinsModel} from '../typeSoins-model';

@Component({
  selector: 'app-list-type-soinss',
  templateUrl: './list-type-soinss.component.html',
  styleUrls: ['./list-type-soinss.component.css']
})
export class ListTypeSoinssComponent implements OnInit {

  subreddits: Array<TypeSoinsModel>;
  constructor(private subredditService: TypeSoinsService) { }

  ngOnInit(): void {
    this.subredditService.getAllSubreddits().subscribe(data => {
      this.subreddits = data;
    }, error => {
      throwError(error);
    });
  }

}
