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

  constructor() {


   }

  setCurrentUser(user: IUser) {
    this.curr_user = user;

  }
}
