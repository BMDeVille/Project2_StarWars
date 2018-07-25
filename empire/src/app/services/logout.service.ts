import { Injectable } from '@angular/core';
import { AngularFireAuth } from 'angularfire2/auth';

@Injectable({
  providedIn: 'root'
})
export class LogoutService {

  constructor(public _logout: AngularFireAuth) { }

  userLogout() {
    this._logout.auth.signOut();
  }


}
