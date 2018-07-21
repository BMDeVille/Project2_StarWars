import { Injectable } from '@angular/core';
import { IPost } from '../db_models/post';
import { IUser } from '../db_models/user';
import { IComment } from '../db_models/comment';
import { IAllegiance } from '../db_models/allegiance';
import { IImage } from '../db_models/image';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProfileService } from './profile.service';

@Injectable({
  providedIn: 'root'
})
export class PostService {
    comment: IComment[];
    posts: IPost[] = [];
    activeUser: IUser;
    allegiance: IAllegiance;
    image: IImage;
  constructor(private _httpServ: HttpClient, private _userService: ProfileService) {
    this.activeUser = _userService.curr_user;
    this.image = {'iid': 1, 'image': ''};
    this.allegiance = {'aid': 1, 'allegiance': 'empire'};
    this.comment = [{'cid': 1, 'body': '... we have cookies.', 'likes': null}, {'cid': 2, 'body': 'I LOVE cookies!', 'likes': null}];
    // this.activeUser = {'id': 1, 'fname': 'Darth', 'lname': 'Vador', 'username': 'SithLord', 'about': '',
    //  'sec_ans': '', 'dob': new Date(), 'allegiance': this.allegiance, 'email': 'd.vador@empire.gov', 'followers': null, 'posts': null,
    //   'image': this.image};
    this.posts = [{'pid': 1, 'body': 'Welcome to the Empire', 'youtube': '', 'created': new Date() ,
     'comments': this.comment, 'likes': [this.activeUser], 'images': null},
    {'pid': 2, 'body': 'Test post', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 3, 'body': 'Test post 3', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 4, 'body': 'Test post 4', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 5, 'body': 'Test post 5', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 6, 'body': 'Test post 6', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 7, 'body': 'Test post 7', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 8, 'body': 'Test post 8', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 9, 'body': 'Test post 9', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 10, 'body': 'Test post 10', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 11, 'body': 'Test post 11', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 12, 'body': 'Test post 12', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 13, 'body': 'Test post 13', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 14, 'body': 'Test post 14', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 14, 'body': 'Test post 14', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 14, 'body': 'Test post 14', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 14, 'body': 'Test post 14', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 14, 'body': 'Test post 14', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 14, 'body': 'Test post 14', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 14, 'body': 'Test post 14', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 14, 'body': 'Test post 14', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 14, 'body': 'Test post 14', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 14, 'body': 'Test post 14', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 14, 'body': 'Test post 14', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 14, 'body': 'Test post 14', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 14, 'body': 'Test post 14', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 14, 'body': 'Test post 14', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 14, 'body': 'Test post 14', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null},
    {'pid': 14, 'body': 'Test post 14', 'youtube': '', 'created': new Date() , 'comments': null, 'likes': null, 'images': null}
  ];
  }

  getFeed(username: String): IPost[] {
    // const _url = 'http://localhost:9005/starwar/feed.ms';
    // const obs: Observable<IPost[]> = this._httpServ.get(_url).pipe(map(resp => resp as IPost[]));
    // obs.subscribe(data => this.posts);
    return this.posts;
  }

  createPost(body: String, youtube: String, filepath: String) {

  }
}
