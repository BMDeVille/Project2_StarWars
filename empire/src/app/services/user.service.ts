import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProfileService } from './profile.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Headers, RequestOptions} from '@angular/http';
import { IUser } from '../db_models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private _profileService: ProfileService, private _httpServ: HttpClient) { }
  private url = 'http://localhost:9005/starwar/login.app';
  private urlT = 'http://localhost:9005/starwar/createAccount.app';
  private urlR = 'http://localhost:9005/starwar/reset.app';

  // login() {
  //   const _url = 'http://localhost:9005/starwar/login.app';
  //   const obs: Observable<IUser> = this._httpServ.get(_url).pipe(
  //     map(resp => resp as IUser)
  //   );
  //   obs.subscribe(data => this._profileService.setCurrentUser(new IUser(data)));
  // }
 httpOptions = { headers: new HttpHeaders({
    'Content-Type': 'application/x-www-form-urlencoded'
  }),
  withCredentials: true
};

  getUser(username: string, password: string): Observable<IUser> {
    // const headers = new Headers();
    // headers.append('Content-Type', 'application/x-www-form-urlencoded');
    // const options = new RequestOptions();
    // options.headers = headers;


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
