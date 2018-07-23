import { Component, OnInit } from '@angular/core';
import { ImageService } from '../services/image.service';
import { IImage } from '../db_models/image';
import { ModalService } from '../services/modal.service';

@Component({
  selector: 'app-images',
  templateUrl: './images.component.html',
  styleUrls: ['./images.component.css']
})
export class ImagesComponent implements OnInit {
  images: IImage[];
  constructor(private _imageService: ImageService, private _modalService: ModalService) {
    this.images = this._imageService.getImages();
    console.log(this.images);
  }

  ngOnInit() {
  }

  close() {
    this._modalService.destroy();
  }
}
