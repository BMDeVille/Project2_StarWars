import { Component, OnInit, AfterViewInit} from '@angular/core';

import { ModalService } from '../services/modal.service';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit, AfterViewInit {

  constructor(private _modalService: ModalService) { }

  ngOnInit() {
  }
  ngAfterViewInit() {
    setTimeout(_ => this.initLoginModal());
  }

   initLoginModal() {
    const inputs = {
      isMobile: false
    };
    this._modalService.init(LoginComponent, inputs, {});
  }

}
