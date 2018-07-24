import { Injectable } from '@angular/core';
import { IPost } from '../db_models/post';
import { IUser } from '../db_models/user';
import { IComment } from '../db_models/comment';
import { IAllegiance } from '../db_models/allegiance';
import { IImage } from '../db_models/image';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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
    this.activeUser = _userService.curr_user;
    this.image1 = {'iid': 2, 'image': 'assets/images/hqdefault.jpg'};
    this.image2 = {'iid': 3, 'image': 'assets/images/5924290001_a49dc23687_b.jpg'};
  }
  httpOptions = { headers: new HttpHeaders({
    'Content-Type': 'application/x-www-form-urlencoded'
  }),
  withCredentials: true
};
  getFeed(username: String): IPost[] {
     const _url = 'http://ec2-18-216-92-54.us-east-2.compute.amazonaws.com:8080/cantina/feed.app';
     const obs: Observable<IPost[]> = this._httpServ.get(_url).pipe(map(resp => resp as IPost[]));
     obs.subscribe(data => this.posts);
    return this.posts;
  }

  getPostById(id: number): IPost {

      return null;
      // return this.posts[id - 1];
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

  createPost(post: IPost): Observable<IPost>  {
    const url = 'url for create post';
    return this._httpServ.post(url, 'body=' + post.body + '&owner=' + post.owner +
    '&youtube=' + post.youtube + '&images=' + post.images, this.httpOptions).pipe(map(resp => resp as IPost));
  }

  updatePost(post: IPost) {

  }

  createComment(com: IComment): Observable<IComment> {
    const url = 'url for create comment';
    return this._httpServ.post(url, 'body=' + com.body + '&postid=' + com.postid +
     '&ownerid=' + com.ownerid, this.httpOptions).pipe(map(resp => resp as IComment));
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
