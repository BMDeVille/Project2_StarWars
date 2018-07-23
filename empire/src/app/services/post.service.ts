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
    // actually need
    activePost: IPost;

    // mostly needed for hardwiring of values
    comment: IComment[];
    posts: IPost[] = [];
    activeUser: IUser;
    allegiance: IAllegiance;
    image1: IImage;
    image2: IImage;
    user: IUser;
  constructor(private _httpServ: HttpClient, private _userService: ProfileService) {
    const allegiance = {'aid': 2, 'allegiance': 'Rebel Alliance'};
    this.user = {'id': 2, 'password': '123asd', 'fname': 'Han', 'lname': 'Solo', 'username': 'Scoundrel', 'about': 'Never tell me the odds',
    'sec_ans': '', 'dob': new Date(), 'allegiance': allegiance, 'email': 'h.solo@reb.org', 'followers': null, 'posts': null,
     'image': null};
    this.activeUser = _userService.curr_user;
    this.image1 = {'iid': 2, 'image': 'assets/images/hqdefault.jpg'};
    this.image2 = {'iid': 3, 'image': 'assets/images/5924290001_a49dc23687_b.jpg'};
    this.allegiance = {'aid': 1, 'allegiance': 'empire'};
    this.comment = [{'cid': 1, 'body': '... we have cookies.', 'likes': null}, {'cid': 2, 'body': 'I LOVE cookies!', 'likes': null}];
    const newComment = [{'cid': 3, 'body': 'Too many drinks last night Han?', 'likes': null}];
    this.posts = [{'pid': 1, 'body': 'Welcome to the Empire', 'youtube': '', 'created': new Date() ,
     'owner': this.activeUser, 'comments': this.comment, 'likes': [this.activeUser], 'images': [this.image1, this.image2]},
    {'pid': 2, 'body': 'Has anyone seen the keys to the Falcon?', 'youtube': '', 'created': new Date() , 'owner': this.user,
     'comments': newComment, 'likes': null, 'images': null},
    {'pid': 3, 'body': 'Test post', 'youtube': '', 'created': new Date() , 'owner': this.activeUser,
     'comments': null, 'likes': null, 'images': null},
    {'pid': 4, 'body': 'Test post', 'youtube': '', 'created': new Date() , 'owner': this.activeUser,
     'comments': null, 'likes': null, 'images': null},
    {'pid': 5, 'body': 'Test post', 'youtube': '', 'created': new Date() , 'owner': this.activeUser,
     'comments': null, 'likes': null, 'images': null},
    {'pid': 6, 'body': 'Test post', 'youtube': '', 'created': new Date() , 'owner': this.activeUser,
     'comments': null, 'likes': null, 'images': null},
    {'pid': 7, 'body': 'Test post', 'youtube': '', 'created': new Date() , 'owner': this.activeUser,
     'comments': null, 'likes': null, 'images': null},
    {'pid': 8, 'body': 'Test post', 'youtube': '', 'created': new Date() , 'owner': this.activeUser,
     'comments': null, 'likes': null, 'images': null},
    {'pid': 9, 'body': 'Test post', 'youtube': '', 'created': new Date() , 'owner': this.activeUser,
     'comments': null, 'likes': null, 'images': null},
    {'pid': 10, 'body': 'Test post', 'youtube': '', 'created': new Date() , 'owner': this.activeUser,
     'comments': null, 'likes': null, 'images': null},
    {'pid': 11, 'body': 'Test post', 'youtube': '', 'created': new Date() , 'owner': this.activeUser,
     'comments': null, 'likes': null, 'images': null}
  ];
  console.log(this.posts);
  this.activePost = {'pid': 1, 'body': 'Welcome to the Empire', 'youtube': '', 'created': new Date() ,
  'owner': this.activeUser, 'comments': this.comment, 'likes': [this.activeUser], 'images': [this.image1, this.image2]};
  }

  getFeed(username: String): IPost[] {
    // const _url = 'http://localhost:9005/starwar/feed.ms';
    // const obs: Observable<IPost[]> = this._httpServ.get(_url).pipe(map(resp => resp as IPost[]));
    // obs.subscribe(data => this.posts);
    return this.posts;
  }

  getPostById(id: number): IPost {
      return this.posts[id - 1];
  }

  getCommentByIdAndPostId(cid: number, pid: number): IComment {
    const cpost = this.getPostById(pid);
    let com;
    for (let i = 0; i < cpost.comments.length; ++i) {
      if (cid === cpost.comments[i].cid) {
        com = cpost.comments[i];
        break;
      }
    }
    return com;
  }

  createPost(post: IPost) {

  }

  updatePost(post: IPost) {

  }

  updateComment(com: IComment) {

  }
  getActivePost(): IPost {
    return this.activePost;
  }

  setActivePost(post: IPost) {
    this.activePost = post;
  }


}
