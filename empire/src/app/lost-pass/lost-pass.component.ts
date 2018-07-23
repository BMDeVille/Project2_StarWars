import { Component, OnInit } from '@angular/core';
import { LoginComponent } from '../login/login.component';
import { ModalService } from '../services/modal.service';

@Component({
  selector: 'app-lost-pass',
  templateUrl: './lost-pass.component.html',
  styleUrls: ['./lost-pass.component.css']
})
export class LostPassComponent implements OnInit {

  constructor(private _modalService: ModalService) { }

  ngOnInit() {
  }

  initLoginModal() {
    const inputs = {
      isMobile: false
    };
    this._modalService.reInit(LoginComponent, inputs, {});
  }

}
