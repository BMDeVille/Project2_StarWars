import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  images: string[];
  constructor() { }

  setImages(is: string[]) {
    this.images = is;
  }

  getImages(): string[] {
    return this.images;
  }

}
