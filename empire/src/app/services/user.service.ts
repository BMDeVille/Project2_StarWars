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
  private url = 'http://localhost:9005/starwar/';
  // use this url and add the uri at the end
  // private url = 'http://ec2-18-217-47-221.us-east-2.compute.amazonaws.com:8080/cantina/';
  public curr_user: Observable<IUser>;
  private urlFP = 'http://ec2-18-217-47-221.us-east-2.compute.amazonaws.com:8080/cantina/forgotPassword.app';
  private urlUS = 'http://ec2-18-217-47-221.us-east-2.compute.amazonaws.com:8080/cantina/updateUser.app';

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
  getSecQues(): string {
    return this._profileService.curr_user.sec_ques;
  }

  forgotPassword(email: string, sec_ans: string) {
    if (this._profileService.curr_user.email === email
        && this._profileService.curr_user.sec_ans === sec_ans) {
      // Send email to user, with link to password reset page
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


}
