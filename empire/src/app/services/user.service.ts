import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProfileService } from './profile.service';
import { HttpClient } from '@angular/common/http';
import { Headers, RequestOptions} from '@angular/http';
import { IUser } from '../db_models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private _profileService: ProfileService, private _httpServ: HttpClient) { }
  private url = 'http://localhost:9005/starwar/login.app';
  private urlT = 'http://localhost:9005/starwar/createAccount.app';

  login() {
  //   const _url = 'http://localhost:9005/starwar/login.app';
  //   const obs: Observable<IUser> = this._httpServ.get(_url).pipe(
  //     map(resp => resp as IUser)
  //   );
  //   obs.subscribe(data => this._profileService.setCurrentUser(new IUser(data)));
  }

  getAllUsers(): IUser[] {
    return null;
  }

  getUser(username: string, password: string): Observable<IUser> {
    // const headers = new Headers();
    // headers.append('Content-Type', 'application/x-www-form-urlencoded');
    // const options = new RequestOptions();
    // options.headers = headers;

    // send username and password to controller
    return this._httpServ.post(this.url, {username: username, password: password}).pipe(map(resp => resp as IUser));
  }

  regUser(reg: any): Observable<IUser> {
    return this._httpServ.post(this.urlT, {reg}).pipe(map(resp => resp as IUser));
  }
}
