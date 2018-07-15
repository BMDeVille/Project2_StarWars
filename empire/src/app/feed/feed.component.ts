import { Component, OnInit, ChangeDetectorRef, AfterViewInit} from '@angular/core';

import { ModalService } from '../services/modal.service';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit, AfterViewInit {

  constructor(private _modalService: ModalService, private _cdr: ChangeDetectorRef) { }

  ngOnInit() {
  }
  ngAfterViewInit() {
    this.initLoginModal();
    this._cdr.detectChanges();
  }

   initLoginModal() {
    const inputs = {
      isMobile: false
    };
    this._modalService.init(LoginComponent, inputs, {});
  }

}
