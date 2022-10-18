import { Component, OnInit } from '@angular/core';
import {TypeSoinsModel} from '../../typeSoins/typeSoins-model';
import {TypeSoinsService} from '../../typeSoins/type-soins.service';

@Component({
  selector: 'app-type-soins-side-bar',
  templateUrl: './type-soins-side-bar.component.html',
  styleUrls: ['./type-soins-side-bar.component.css']
})
export class TypeSoinsSideBarComponent implements OnInit {

  subreddits: Array<TypeSoinsModel> = [];
  displayViewAll: boolean;



  constructor(private typeSoinsService: TypeSoinsService) {
    this.typeSoinsService.getAllSubreddits().subscribe(data => {
      if (data.length > 3) {
        this.subreddits = data.splice(0, 3);
        this.displayViewAll = true;
      } else {
        this.subreddits = data;
      }
    });
  }

  ngOnInit(): void {
  }

}
