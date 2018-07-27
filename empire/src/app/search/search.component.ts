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
    // setTimeout(_ => this.allUsers = this._userService.getDemBois(), 1000);
    this.searchCond = this._searchService.getSearchCond();
    this.userSearch = [];
    this.search();
    // setTimeout(_ => this.search(), 1100);
   }

  ngOnInit() {
  }

  search() {
    // console.log(this.searchCond);
    // console.log(this.allUsers);
    // console.log(this.userSearch);
    const regEx = new RegExp(this.searchCond.toLowerCase());
    for (let i = 0; i < this.allUsers.length; ++i) {
      if ((this.allUsers[i].fname.toLowerCase().search(regEx) !== -1) || (this.allUsers[i].lname.toLowerCase().search(regEx) !== -1)) {
        this.userSearch.push(this.allUsers[i]);
      }
    }
  }

  setViewUser() {
    // console.log((<HTMLElement>event.target).parentElement.id);
    // get user by id
    const id = (<HTMLElement>event.target).parentElement.id;
    this._userService.getUserByFirstName(id);
    const user: IUser = this._userService.curr_user;
    // assign user to viewUser
    this._profileService.setViewUser(user);
    this.router.navigateByUrl('/profile');
  }
}
