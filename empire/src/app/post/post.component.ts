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
    console.log('in file change');
    console.log(document.getElementById('image').value);
    const filename = document.getElementById('image').value.split('\\');
    document.getElementById('filepath').value = filename[filename.length -1];
  }
}
