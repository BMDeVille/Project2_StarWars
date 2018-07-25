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
  private url = 'http://ec2-18-217-48-227.us-east-2.compute.amazonaws.com:8080/cantina/';
  public curr_user: IUser;
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

  setCurrUser(user: IUser) {
    this.curr_user = user;
  }

  getUserById(num: number) {
    // const alluser = this.getAllUsers();
    // for (let i = 0; i < alluser.length; ++i) {
    //   if (alluser[i].id === num) {
    //     return alluser[i];
    //   }
    // }
    this._httpServ.post(this.url + '', 'first_name=' + name, this.httpOptions).pipe(map(resp => resp as IUser))
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

  forgotPassword(user: IUser, email: string) {
    if (user !== null) {
      console.log(user);
      // Send email to user, with link to password reset page
      this._httpServ.post(this.url + 'email.app', 'email=' + email, this.httpOptions);
    }
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
      return this._httpServ.post(this.url + 'updateUser.app', 'username=' + up.username + '&firstname=' + up.firstName
      + '&lastname' + up.lastName + '&email' + up.email + '&about' + up.about + '&dob' + up.dob,
      this.httpOptions).pipe(map(resp => resp as IUser));
    }
}
