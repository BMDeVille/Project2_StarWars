import { Component, AfterViewInit, OnInit} from '@angular/core';
import { LoginComponent } from './login/login.component';
import { ModalService } from './services/modal.service';
import { ProfileService } from './services/profile.service';
import { IUser } from './db_models/user';
import { SearchService } from './services/search.service';
import { Router } from '@angular/router';
import { LogoutService } from './services/logout.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit, OnInit {
  title = 'The Cantina';

  faviconArray: string[];
  faviconSrc: string;
  constructor(private _modalService: ModalService, private _profileService: ProfileService,
     private _searchService: SearchService, private router: Router, private auth: LogoutService) {
    this.CountDownTimer('12/20/2019 5:35 PM', 'newcountdown');
    this.faviconArray = ['http://icons.iconarchive.com/icons/sensibleworld/starwars/48/Death-Star-icon.png',
                        'assets/images/rsz_1star_wars__galactic_empire_neon_logo_wp_by_morganrlewis-d9snpkl_1.png',
                        'assets/images/rsz_star-wars-rebel-logo-wallpaper.png'];
    this.faviconSrc = this.faviconArray[0];
  }

  ngOnInit() {
      this._profileService.userChange.subscribe(num => this.changeFavicon(num));
  }

  ngAfterViewInit() {
    setTimeout(_ => this.initLoginModal());
  }

  logout() {
    this.auth.userLogout();
    this.initLoginModal();
  }

  search() {
    console.log('in search');
    const str = (<HTMLInputElement>document.getElementById('txtSearch')).value;
    this._searchService.setSearchCond(str);
    // router link to search component
    this.router.navigateByUrl('/search');
  }

  changeFavicon(num: number) {
    console.log('favicon change');
    this.faviconSrc = this.faviconArray[num];
    console.log((<HTMLBodyElement>document.getElementsByTagName('body')[0])); // = 'url("assets/images/Coruscant_at_night.jpg")';
  }

  initLoginModal() {
    const inputs = {
      isMobile: false
    };
    this._modalService.init(LoginComponent, inputs, {});
  }

CountDownTimer(dt, id) {
  let end: any;
  end = new Date(dt);
  let _second: number;
  let _minute: number;
  let _hour: number;
  let _day: number;
  _second = 1000;
  _minute = _second * 60;
  _hour = _minute * 60;
  _day = _hour * 24;
  let timer;

  function showRemaining() {
    let now: any;
    now = new Date();

    let distance: any;
    distance = end - now;
      if (distance < 0) {
          clearInterval(timer);
          document.getElementById(id).innerHTML = 'EXPIRED!';
          return;
      }
      let days: any;
      days = Math.floor(distance / _day);
      let hours: any;
      hours = Math.floor((distance % _day) / _hour);
      let minutes: any;
      minutes = Math.floor((distance % _hour) / _minute);
      let seconds: any;
      seconds = Math.floor((distance % _minute) / _second);

      if (String(hours).length < 2) {
          hours = 0 + String(hours);
      }
      if (String(minutes).length < 2) {
          minutes = 0 + String(minutes);
      }
      if (String(seconds).length < 2) {
          seconds = 0 + String(seconds);
      }


      let datestr: any;
      datestr = days + ' days ' +
                    hours + ' hrs ' +
                    minutes + ' mins ' +
                    seconds + ' secs';

      document.getElementById(id).innerHTML = datestr;
  }

  timer = setInterval(showRemaining, 1000);
}



}
