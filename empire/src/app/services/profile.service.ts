import { Injectable } from '@angular/core';
import { IUser } from '../db_models/user';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  users: IUser[] = [];
  curr_user: IUser;

  constructor() { }

  setCurrentUser(user: IUser) {
    this.curr_user = user;
  }
}
