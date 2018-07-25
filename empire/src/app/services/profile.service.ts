import { Injectable, Output, EventEmitter } from '@angular/core';
import { IUser } from '../db_models/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private clock: Observable<Date>;


  users: IUser[] = [];
  curr_user: IUser;
  view_user: IUser;
  @Output() userChange = new EventEmitter<Number>();

  constructor() {
   }

  setCurrentUser(user: IUser) {
    // this.curr_user = user;
    console.log('emit change');
    this.curr_user = user;
    console.log(this.curr_user);
    console.log(this.curr_user.allegiance);
    this.userChange.emit(this.curr_user.allegiance.aid);
  }

  setViewUser(user: IUser) {
    this.view_user = user;
  }
  getCurrentUser(): IUser {
    console.log(this.curr_user);
    return this.curr_user;
  }

  getViewUser(): IUser {
    return this.view_user;
  }
}
