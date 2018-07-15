import { Component, OnInit } from '@angular/core';

import { ModalService } from '../services/modal.service';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit {

  constructor(private _modalService: ModalService) { }

  ngOnInit() {
    this.initLoginModal();
  }

   initLoginModal() {
    const inputs = {
      isMobile: true
    };
    this._modalService.init(LoginComponent, inputs, {});
  }

}
