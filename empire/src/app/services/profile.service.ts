import { Injectable, Output, EventEmitter } from '@angular/core';
import { IUser } from '../db_models/user';
import { Observable } from 'rxjs';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private clock: Observable<Date>;


  users: IUser[] = [];
  curr_user: IUser;
  view_user: IUser;
  @Output() userChange = new EventEmitter<Number>();

  constructor(/* private _userService: UserService */) {
   }

  setCurrentUser(user: IUser) {
    this.curr_user = user;
    this.setViewUser(user);
    // console.log(localStorage.getItem('currentUser'));
    console.log('in set current');
    console.log(this.curr_user);
  }

  setViewUser(user: IUser) {
    console.log('in set view');
    console.log(user);
    this.view_user = user;
  }

  getCurrentUser(): IUser {
    return this.curr_user;
  }

  getViewUser(): IUser {
    return this.view_user;
  }
}
