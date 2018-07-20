import { Component, AfterViewInit, ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit {
  title = 'The Cantina';

  constructor(private _cdr: ChangeDetectorRef) {
    this.CountDownTimer('12/20/2019 5:35 PM', 'newcountdown');
  }

  ngAfterViewInit() {
    this._cdr.detectChanges();
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
