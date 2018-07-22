import { Injectable } from '@angular/core';
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

  constructor() {
    const image = {'iid': 1, 'image': 'https://static.comicvine.com/uploads/original/11121/111213005/4542373-9252677329-15219.jpg'};
    const allegiance = {'aid': 1, 'allegiance': 'Galactic Empire'};
    this.curr_user = {'id': 1, 'fname': 'Darth', 'lname': 'Vader', 'username': 'SithLord', 'about': 'likes cookies',
     'sec_ans': '', 'dob': new Date(), 'allegiance': allegiance, 'email': 'd.vador@empire.gov', 'followers': null, 'posts': null,
      'image': image};
    this.view_user = this.curr_user;
   }

  setCurrentUser(user: IUser) {
    // this.curr_user = user;
  }

  setViewUser(user: IUser) {
    this.view_user = user;
  }
  getCurrentUser(): IUser {
    return this.curr_user;
  }

  getViewUser(): IUser {
    return this.view_user;
  }
}
