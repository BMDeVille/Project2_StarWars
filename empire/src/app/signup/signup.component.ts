import { Component, OnInit, ChangeDetectorRef, AfterViewInit } from '@angular/core';
import { ModalService } from '../services/modal.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit, AfterViewInit {

  constructor(private _modalService: ModalService, private _cdr: ChangeDetectorRef) { }

  ngOnInit() {
  }

  ngAfterViewInit() {
    this._cdr.detectChanges();
  }

  public close() {
    this._modalService.destroy();
  }

}
