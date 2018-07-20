import { Component, OnInit } from '@angular/core';
import { ModalService } from '../services/modal.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  constructor(private _modalService: ModalService) { }

  ngOnInit() {
  }

  public close() {
    this._modalService.destroy();
  }

  fileNameChanged(event: { target: HTMLInputElement; }) {
    const filename = (<HTMLInputElement>document.getElementById('image')).value.split('\\');
    (<HTMLInputElement>document.getElementById('filepath')).value = filename[filename.length - 1];
  }

  public createPost() {
    this._modalService.destroy();
  }
}
