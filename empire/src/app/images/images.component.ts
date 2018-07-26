import { Component, OnInit } from '@angular/core';
import { ImageService } from '../services/image.service';
import { ModalService } from '../services/modal.service';
import { stringType } from '../../../node_modules/aws-sdk/clients/iam';

@Component({
  selector: 'app-images',
  templateUrl: './images.component.html',
  styleUrls: ['./images.component.css']
})
export class ImagesComponent implements OnInit {
  images: stringType[];
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
