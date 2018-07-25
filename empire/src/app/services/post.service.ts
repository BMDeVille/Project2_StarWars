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
    comments: IComment[];
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
    console.log('getting feed');
    this.posts = [];
     // const _url = 'http://ec2-18-216-92-54.us-east-2.compute.amazonaws.com:8080/cantina/allFeed.app';
    const _url = 'http://localhost:9005/starwar/allFeed.app';
    const obs: Observable<IPost[]> = this._httpServ.get(_url).pipe(map(resp => resp as IPost[]));
     obs.subscribe(data => this.postMapper(data));
     // obs.subscribe(data => console.log(data));
     console.log('length in get feed' + this.posts.length);
     // this.getComments();
     // this.mapCommentToPost();
    return this.posts;
  }

  getComments() {
    console.log('getting comments');
    this.comments = [];
    const _url = 'http://localhost:9005/starwar/allComments.app';
    const obs: Observable<IComment[]> = this._httpServ.get(_url).pipe(map(resp => resp as IComment[]));
     obs.subscribe(data => this.commentMapper(data));
     // obs.subscribe(data => console.log(data));
     console.log(this.comments);
     return null;
  }
  postMapper(obs: IPost[]) {
    for (const ob of obs) {
      this.posts.push(new IPost(ob));
    }
    this.getComments();
  }

  commentMapper(obs: IComment[]) {
    for (const ob of obs) {
      this.comments.push(new IComment(ob));
    }
    this.mapCommentToPost();
  }

  mapCommentToPost() {
    console.log('mapping comments to posts');
    console.log('length in mapping' + this.posts.length);
    for (let i = 0; i < this.posts.length; ++i) {
      this.posts[i].comments = [];
      for (let j = 0; j < this.comments.length; ++j) {
        console.log('post id: ' + this.posts[i].pid);
        console.log('comment post id: ' + this.comments[j].post.pid);
        if (this.posts[i].pid === this.comments[j].post.pid) {
            this.posts[i].comments.push(this.comments[j]);
            console.log(this.posts[i].comments);
        }
      }
    }
    return null;
  }
  getPostById(id: number): IPost {
      let post;
      for (let i = 0; i < this.posts.length; ++i) {
        if (id === this.posts[i].pid) {
          post = this.posts[i];
        }
      }
      return post;
  }

  getCommentById(cid: number): IComment {
    let com;
    for (let i = 0; i < this.comments.length; ++i) {
      if (cid === this.comments[i].cid) {
        com = this.comments[i];
        break;
      }
    }
    return com;
  }

  createPost(post: IPost): Observable<IPost>  {
    const url = 'url for create post';
    return this._httpServ.post(url, 'body=' + post.body + '&owner=' + post.creator +
    '&youtube=' + post.youtube + '&images=' + post.images, this.httpOptions).pipe(map(resp => resp as IPost));
  }

  updatePost(post: IPost) {

  }

  createComment(com: IComment): Observable<IComment> {
    const url = 'url for create comment';
    return this._httpServ.post(url, 'body=' + com.body + '&post=' + com.post +
     '&poster=' + com.poster, this.httpOptions).pipe(map(resp => resp as IComment));
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
