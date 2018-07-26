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
<<<<<<< HEAD
    this.userChange.emit(JSON.parse(localStorage.getItem('currentUser')).allegiance.aid);
    this.setViewUser(user);
=======
    this.userChange.emit(this.curr_user.allegiance.aid);
>>>>>>> dba005877ffdd5a90d1b64a265f2f265dd4ce750
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
