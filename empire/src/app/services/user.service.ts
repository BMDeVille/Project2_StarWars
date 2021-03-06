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
  users: IUser[];
  constructor(private _profileService: ProfileService, private _httpServ: HttpClient) {
    this.getUsers();
   }
  // private url = 'http://localhost:9005/starwar/';
  private url = 'http://ec2-18-191-203-45.us-east-2.compute.amazonaws.com:8080/cantina/';
  public curr_user: IUser;

  httpOptions = { headers: new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded'
    }),
    withCredentials: true
  };

  getAllUsers(): IUser[] {
    return this.users;
  }
  getUsers() {
    const obs: Observable<IUser[]> = this._httpServ.get(this.url + 'get-users.app').pipe(map(resp => resp as IUser[]));
     obs.subscribe(data => this.mapUsers(data));
  }

  // getDemBois(): IUser[] {
  //   console.log(this.users);
  //   return this.users;
  // }
  mapUsers(obs: IUser[]) {
    this.users = [];
    for (const ob of obs) {
      this.users.push(new IUser(ob));
    }
   // console.log(this.users);
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
  getUserById(name: string) {
    // const alluser = this.getAllUsers();
    // for (let i = 0; i < alluser.length; ++i) {
    //   if (alluser[i].id === num) {
    //     return alluser[i];
    //   }
    // }
    this._httpServ.post(this.url + 'get-by-id', 'id=' + name, this.httpOptions).pipe(map(resp => resp as IUser))
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
    // .subscribe(data => this._profileService.setCurrentUser(data));
    .subscribe(data => localStorage.setItem('currentUser', JSON.stringify(data)));
    this._profileService.setCurrentUser(JSON.parse(localStorage.getItem('currentUser')));
  }
    forgotPassword(user: IUser, email: string) {
    if (user !== null) {
      // console.log(user);
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
     this._httpServ.post(this.url + 'updateAccount.app', 'username=' + up.username + '&firstname=' + up.fname
      + '&lastname=' + up.lname + '&email=' + this._profileService.curr_user.email + '&about=' + up.about,
      this.httpOptions).pipe(map(resp => resp as IUser)).subscribe(data => this._profileService.setCurrentUser(data));
    }

    updateProfilePicture(pic: any) {
      // console.log('in update profile picture');
      // console.log(pic);
      this._httpServ.post(this.url + 'updateProfilePicture.app', 'image=' + pic.location +
      '&username=' + this._profileService.getCurrentUser().username , this.httpOptions).
      pipe(map(resp => resp as IUser)).subscribe(data => this._profileService.setCurrentUser(data));
    }
}
