import { Component, OnInit } from '@angular/core';
import { IUser } from '../db_models/user';
import { UserService } from '../services/user.service';
import { SearchService } from '../services/search.service';
import { ProfileService } from '../services/profile.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  userSearch: IUser[];
  allUsers: IUser[];
  searchCond: string;
  constructor(private _userService: UserService, private _searchService: SearchService,
     private _profileService: ProfileService, private router: Router) {
    this.allUsers = this._userService.getAllUsers();
    this.searchCond = this._searchService.getSearchCond();
    this.userSearch = [];
    this.search();
   }

  ngOnInit() {
  }

  search() {
    console.log(this.userSearch);
    const regEx = new RegExp(this.searchCond.toLowerCase());
    for (let i = 0; i < this.allUsers.length; ++i) {
      if ((this.allUsers[i].fname.toLowerCase().search(regEx) !== -1) || (this.allUsers[i].lname.toLowerCase().search(regEx) !== -1)) {
        this.userSearch.push(this.allUsers[i]);
      }
    }
  }

  setViewUser() {
    console.log((<HTMLElement>event.target).parentElement.id);
    // get user by id
    const id = (<HTMLElement>event.target).parentElement.id;
    const user = this._userService.getUserByFirstName(id);
    // assign user to viewUser
    user.subscribe(data => this._profileService.setViewUser(new IUser(data)));
    this.router.navigateByUrl('/profile');
  }
}
