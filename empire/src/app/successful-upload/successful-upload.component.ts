import { Component, OnInit } from '@angular/core';
import { ModalService } from '../services/modal.service';

@Component({
  selector: 'app-successful-upload',
  templateUrl: './successful-upload.component.html',
  styleUrls: ['./successful-upload.component.css']
})
export class SuccessfulUploadComponent implements OnInit {

  constructor(private _modal: ModalService) { }

  ngOnInit() {
  }

  close() {
    this._modal.destroy();
  }

}
