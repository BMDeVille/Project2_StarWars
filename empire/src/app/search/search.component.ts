import { Component, OnInit } from '@angular/core';
import { IUser } from '../db_models/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  userSearch: IUser[];
  allUsers: IUser[];
  searchCond: string;
  constructor(private _userService: UserService) {
    this.allUsers = this._userService.getAllUsers();
   }

  ngOnInit() {
  }

  search() {
    const regEx = new RegExp(this.searchCond.toLowerCase());
    for (let i = 0; i < this.allUsers.length; ++i) {
      if ((this.allUsers[i].fname.toLowerCase().search(regEx) !== -1) || (this.allUsers[i].lname.toLowerCase().search(regEx) !== -1)) {
        this.userSearch.push(this.allUsers[i]);
      }
    }
  }
}
