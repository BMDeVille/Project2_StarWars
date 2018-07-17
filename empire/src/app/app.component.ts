import { Component, AfterViewInit, ChangeDetectorRef } from '@angular/core';

// test jenkins build
// test 2
// test 3

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit {
  title = 'The Cantina';

  constructor(private _cdr: ChangeDetectorRef) {}

  ngAfterViewInit() {
    this._cdr.detectChanges();
  }

}
