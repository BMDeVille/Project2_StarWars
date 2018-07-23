import { Injectable } from '@angular/core';
import { IImage } from '../db_models/image';

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  images: IImage[];
  constructor() { }

  setImages(is: IImage[]) {
    this.images = is;
  }

  getImages(): IImage[] {
    return this.images;
  }
}
