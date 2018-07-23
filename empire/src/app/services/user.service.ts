import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProfileService } from './profile.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { IUser } from '../db_models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private _profileService: ProfileService, private _httpServ: HttpClient) { }
  // private url = 'http://localhost:9005/starwar/login.app';
  private url = 'http://ec2-18-222-198-157.us-east-2.compute.amazonaws.com:8080/cantina/login.app';
  private urlT = 'http://localhost:9005/starwar/createAccount.app';
  private urlR = 'http://localhost:9005/starwar/reset.app';


 httpOptions = { headers: new HttpHeaders({
    'Content-Type': 'application/x-www-form-urlencoded'
  }),
  withCredentials: true
};

  getAllUsers(): IUser[] {
    const allegiance = {'aid': 1, 'allegiance': 'Galactic Empire'};
    const users = [{'id': 1, 'password': '123asd', 'fname': 'Darth', 'lname': 'Vader', 'username': 'SithLord', 'about': 'likes cookies',
     'sec_ans': '', 'dob': new Date(), 'allegiance': allegiance, 'email': 'd.vador@empire.gov', 'followers': null, 'posts': null,
      'image': null},
      {'id': 2, 'password': '123asd', 'fname': 'Han', 'lname': 'Solo', 'username': 'Scoundrel', 'about': 'Never tell me the odds',
      'sec_ans': '', 'dob': new Date(), 'allegiance': allegiance, 'email': 'h.solo@reb.org', 'followers': null, 'posts': null,
       'image': null}];
    return users;
  }

  getUserById(num: number): IUser {
    const alluser = this.getAllUsers();
    for (let i = 0; i < alluser.length; ++i) {
      if (alluser[i].id === num) {
        return alluser[i];
      }
    }
  }
  getUser(username: string, password: string): Observable<IUser> {
    // send username and password to controller
    // then receive json User object
    return this._httpServ.post(this.url, 'username=' + username + '&password=' + password, this.httpOptions)
    .pipe(map(resp => resp as IUser));
  }
  // send register information object to controller
  // receive user object back
  regUser(reg: any): Observable<IUser> {
    return this._httpServ.post(this.urlT, 'username=' + reg.username + '&password=' + reg.password
    + '&firstname=' + reg.firstName + '&lastname=' + reg.lastName + '&email='
    + reg.email + '&date=' + reg.DOB + '&type=' + reg.type , this.httpOptions).pipe(map(resp => resp as IUser));
  }


}
