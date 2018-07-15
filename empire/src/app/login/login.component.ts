import { Component, OnInit, ChangeDetectorRef, AfterViewInit} from '@angular/core';
import { ModalService } from '../services/modal.service';
import { SignupComponent } from '../signup/signup.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, AfterViewInit {

  constructor(private _modalService: ModalService, private _cdr: ChangeDetectorRef) { }

  ngOnInit() {
  }

  ngAfterViewInit() {
    this._cdr.detectChanges();
  }

  public close() {
    this._modalService.destroy();
  }

  initSignupModal() {
    const inputs = {
      isMobile: false
    };
    this._modalService.reInit(SignupComponent, inputs, {});
  }

}
