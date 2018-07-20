import { Component, OnInit } from '@angular/core';
import { ModalService } from '../services/modal.service';
import { UserinformationComponent } from '../userinformation/userinformation.component';

@Component({
  selector: 'app-changeinformation',
  templateUrl: './changeinformation.component.html',
  styleUrls: ['./changeinformation.component.css']
})
export class ChangeinformationComponent implements OnInit {

  constructor(private _modalService: ModalService) { }

  ngOnInit() {

  }

  public close() {
    this._modalService.destroy();
  }

  initAboutModal() {
    const inputs = {
      isMobile: false,
    };
    this._modalService.reInit(UserinformationComponent, inputs, {});
  }

}
