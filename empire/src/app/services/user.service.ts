import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProfileService } from './profile.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { IUser } from '../db_models/user';
import { invalidUserTypeMessage } from 'aws-sdk/clients/iam';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private _profileService: ProfileService, private _httpServ: HttpClient) { }
  // private urlQ = 'http://localhost:9001/starwar/createAccount.app';
  private url = 'http://ec2-18-217-47-221.us-east-2.compute.amazonaws.com:8080/cantina/login.app';
  private urlT = 'http://ec2-18-217-47-221.us-east-2.compute.amazonaws.com:8080/cantina/createAccount.app';
  private urlR = 'http://ec2-18-217-47-221.us-east-2.compute.amazonaws.com:8080/cantina/reset.app';
  public curr_user: Observable<IUser>;
  private urlFP = 'http://ec2-18-217-47-221.us-east-2.compute.amazonaws.com:8080/cantina/email.app';
  private urlUU = 'http://ec2-18-217-47-221.us-east-2.compute.amazonaws.com:8080/cantina/updateUser.app';

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
  getUserByFirstName(name: string): Observable<IUser> {
    // const alluser = this.getAllUsers();
    // for (let i = 0; i < alluser.length; ++i) {
    //   if (alluser[i].id === num) {
    //     return alluser[i];
    //   }
    // }
    const url = 'url for get by id';
    return this._httpServ.post(url, 'first_name=' + name, this.httpOptions).pipe(map(resp => resp as IUser));
  }

  forgotPassword(email: string) {
    if (this._profileService.curr_user.email === email) {
      // Send email to user, with link to password reset page
      this._httpServ.get(this.urlFP, this.httpOptions);
    }
  }

  getUser(user: IUser): Observable<IUser> {
    // send username and password to controller
    // then receive json User object
    this.curr_user = this._httpServ.post(this.url + 'login.app', 'username=' + user.username
    + '&password=' + user.password, this.httpOptions)
    .pipe(map(resp => resp as IUser));
    console.log(this.curr_user);
    return this.curr_user;
  }
  // send register information object to controller
  // receive user object back
  regUser(reg: any): Observable<IUser> {
    return this._httpServ.post(this.url + 'createAccount.app', 'username=' + reg.username + '&password=' + reg.password
    + '&firstname=' + reg.firstName + '&lastname=' + reg.lastName + '&email='
    + reg.email + '&date=' + reg.DOB + '&type=' + reg.type + '&ques=' + reg.sec_ques
    + '&ans=' + reg.sec_ans, this.httpOptions).pipe(map(resp => resp as IUser));  }

    // updating the user information
    updateUser(up: any): Observable<IUser> {
      return this._httpServ.post(this.urlUU, 'username=' + up.username + '&firstname=' + up.firstName
      + '&lastname' + up.lastName + '&email' + up.email + '&about' + up.about + '&dob' + up.dob,
      this.httpOptions).pipe(map(resp => resp as IUser));
    }
}
