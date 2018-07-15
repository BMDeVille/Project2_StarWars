import { Injectable } from '@angular/core';
import { IPost } from '../db_models/post';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  posts: IPost[] = [];

  constructor() { }
}
