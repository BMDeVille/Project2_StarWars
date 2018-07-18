import { Injectable } from '@angular/core';
import { IPost } from '../db_models/post';
import { IUser } from '../db_models/user';
import { IComment } from '../db_models/comment';

@Injectable({
  providedIn: 'root'
})
export class PostService {
    comment: IComment[];
    posts: IPost[] = [];
    user: IUser;
  constructor() {
    this.comment = [{'cid': 1, 'body': '... we have cookies.', 'likes': null}, {'cid': 2, 'body': 'I LOVE cookies!', 'likes': null}];
    this.user = {'id': 1, 'fname': 'Darth', 'lname': 'Vador', 'username': 'SithLord', 'about': '',
     'sec_ans': '', 'dob': new Date(), 'allegiance': 1, 'email': 'd.vador@empire.gov', 'followers': null, 'posts': null, 'image': 1};
    this.posts = [{'pid': 1, 'body': 'Welcome to the Empire', 'youtube': '', 'created': new Date() ,
     'comments': this.comment, 'likes': [this.user], 'images': null},
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

    return this.posts;
  }
}
