import {Component, Input, OnInit} from '@angular/core';
import {SoinsModel} from '../soins-model';
import {faComments} from '@fortawesome/free-solid-svg-icons';
import {PostService} from '../post.service';
import {Router} from '@angular/router';
import {LocalStorageService} from 'ngx-webstorage';

@Component({
  selector: 'app-soins-tile',
  templateUrl: './soins-tile.component.html',
  styleUrls: ['./soins-tile.component.css']
})
export class SoinsTileComponent implements OnInit {

  @Input() posts: Array<SoinsModel>;
  faComments = faComments;
  username = this.getUserName();


  constructor(private router: Router, private localStorage: LocalStorageService) {
  }

  ngOnInit(): void {
  }

  goToPost(id: number): void {
    this.router.navigateByUrl('/view-soins/' + id);
  }
  getUserName() {
    return this.localStorage.retrieve('username');
  }
}
