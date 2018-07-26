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
  private url = 'http://localhost:9001/starwar/';
  // private url = 'http://ec2-18-217-48-227.us-east-2.compute.amazonaws.com:8080/cantina/';
  public curr_user: IUser;



  httpOptions = { headers: new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded'
    }),
    withCredentials: true
  };

  getAllUsers(): IUser[] {

    return null;
  }

  setCurrUser(user: IUser) {
    this.curr_user = user;
  }
  getCurrUser() {
    return this.curr_user;
  }

  getUserByUsername(username: string) {
    // const alluser = this.getAllUsers();
    // for (let i = 0; i < alluser.length; ++i) {
    //   if (alluser[i].id === num) {
    //     return alluser[i];
    //   }
    // }
    this._httpServ.post(this.url + 'get-by-username.app', 'username=' + username, this.httpOptions).pipe(map(resp => resp as IUser))
    .subscribe(data => this.setCurrUser(new IUser(data)));
  }
  getUserByFirstName(name: string) {
    // const alluser = this.getAllUsers();
    // for (let i = 0; i < alluser.length; ++i) {
    //   if (alluser[i].id === num) {
    //     return alluser[i];
    //   }
    // }
    this._httpServ.post(this.url + '', 'first_name=' + name, this.httpOptions).pipe(map(resp => resp as IUser))
    .subscribe(data => this.setCurrUser(new IUser(data)));
  }

  getUserByEmail(email: string) {
    const user: any = this._httpServ.post(this.url + 'get-by-email.app', 'email=' + email, this.httpOptions)
                      .pipe(map(resp => resp as IUser));
    user.subscribe(data => this.forgotPassword(new IUser(data), email));
  }

  // getUserByEmail1(email: string) {
  //  this._httpServ.post(this.url + 'get-by-email.app', 'email=' + email, this.httpOptions)
  //           .pipe(map(resp => resp as IUser))
  //         .subscribe(data => this._profileService.setCurrentUser(data));
  // }

  getUser(user: IUser) {
    // send username and password to controller
    // then receive json User object
      this._httpServ.post(this.url + 'login.app', 'username=' + user.username
    + '&password=' + user.password, this.httpOptions).pipe(map(resp => resp as IUser))
    .subscribe(data => this._profileService.setCurrentUser(data));
  }
    forgotPassword(user: IUser, email: string) {
    if (user !== null) {
      console.log(user);
      // Send email to user, with link to password reset page
      this._httpServ.post(this.url + 'email.app', 'email=' + email, this.httpOptions).subscribe(data => console.log(data));
    }
  }

  resetPassword(username: string, password: string, sec_ans: string) {
    this._httpServ.post(this.url + 'reset.app', 'username=' + username +
      '&sec_ans=' + sec_ans + '&newPass=' + password, this.httpOptions).subscribe(data => console.log(data));
  }

  // send register information object to controller
  // receive user object back
  regUser(reg: any): Observable<IUser> {
    return this._httpServ.post(this.url + 'createAccount.app', 'username=' + reg.username + '&password=' + reg.password
    + '&firstname=' + reg.firstName + '&lastname=' + reg.lastName + '&email='
    + reg.email + '&date=' + reg.DOB + '&type=' + reg.type + '&ques=' + reg.sec_ques
    + '&ans=' + reg.sec_ans, this.httpOptions).pipe(map(resp => resp as IUser));  }

    // updating the user information
    updateUser(up: any) {
     this._httpServ.post(this.url + 'updateAccount.app', 'username=' + up.username + '&firstname=' + up.firstName
      + '&lastname=' + up.lastName + '&email=' + this._profileService.curr_user.email + '&about=' + up.about + '&dob=' + up.dob,
      this.httpOptions).pipe(map(resp => resp as IUser)).subscribe(data => this._profileService.setCurrentUser(data));
    }
}
