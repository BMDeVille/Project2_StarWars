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
  private url = 'http://ec2-18-216-92-54.us-east-2.compute.amazonaws.com:8080/cantina/login.app';
  // private urlT = 'http://localhost:9005/starwar/createAccount.app';
  private urlT = 'http://ec2-18-216-92-54.us-east-2.compute.amazonaws.com:8080/cantina/createAccount.app';
  private urlR = 'http://ec2-18-216-92-54.us-east-2.compute.amazonaws.com:8080/cantina/reset.app';

 httpOptions = { headers: new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded'
    }),
    withCredentials: true
  };

  getAllUsers(): IUser[] {

    return null;
  }

  getUserById(num: number): Observable<IUser> {
    // const alluser = this.getAllUsers();
    // for (let i = 0; i < alluser.length; ++i) {
    //   if (alluser[i].id === num) {
    //     return alluser[i];
    //   }
    // }
    const url = 'url for get by id';
    return this._httpServ.post(url, 'id=' + num, this.httpOptions).pipe(map(resp => resp as IUser));
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
