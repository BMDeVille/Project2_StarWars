import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProfileService } from './profile.service';
import { HttpClient } from '@angular/common/http';
import { IUser } from '../db_models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private _profileService: ProfileService, private _httpServ: HttpClient) { }

  login() {
    const _url = 'http://localhost:9005/starwar/login.ms';
    const obs: Observable<IUser> = this._httpServ.get(_url).pipe(
      map(resp => resp as IUser)
    );
    obs.subscribe(data => this._profileService.setCurrentUser(new IUser(data)));
  }
}
