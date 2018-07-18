import { Component, OnInit } from '@angular/core';
import { ModalService } from '../services/modal.service';

@Component({
  selector: 'app-userinformation',
  templateUrl: './userinformation.component.html',
  styleUrls: ['./userinformation.component.css']
})
export class UserinformationComponent implements OnInit {

  constructor( private _modal: ModalService) { }

  ngOnInit() {
  }

  close() {
    this._modal.destroy();
  }

}
