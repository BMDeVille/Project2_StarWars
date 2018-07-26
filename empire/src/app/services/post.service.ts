import { Injectable } from '@angular/core';
import { IPost } from '../db_models/post';
import { IUser } from '../db_models/user';
import { IComment } from '../db_models/comment';
import { IAllegiance } from '../db_models/allegiance';
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
    _url = 'http://ec2-18-191-203-45.us-east-2.compute.amazonaws.com:8080/cantina/';
    // _url = 'http://localhost:9001/starwar/';

    // mostly needed for hardwiring of values
    comments: IComment[];
    posts: IPost[] = [];
    activeUser: IUser;
    allegiance: IAllegiance;
    image1: string;
    image2: string;
    user: IUser;
    comLikes: IUser[];
    postLikes: IUser[];

  constructor(private _httpServ: HttpClient, private _profileService: ProfileService) {
    this.activeUser = _profileService.getCurrentUser();
    this.image1 = 'assets/images/hqdefault.jpg';
    this.image2 = 'assets/images/5924290001_a49dc23687_b.jpg';
  }
  httpOptions = { headers: new HttpHeaders({
    'Content-Type': 'application/x-www-form-urlencoded'
  }),
  withCredentials: true
};
  getFeed(): IPost[] {
    this.posts = [];
    const obs: Observable<IPost[]> = this._httpServ.get(this._url + 'allFeed.app').pipe(map(resp => resp as IPost[]));
     obs.subscribe(data => this.postMapper(data));
     // obs.subscribe(data => console.log(data));
     setTimeout(_ => console.log('length in get feed' + this.posts.length), 5000);
     // this.getComments();
     // this.mapCommentToPost();
    return this.posts;
  }

  getComments() {
    this.comments = [];
    const obs: Observable<IComment[]> = this._httpServ.get(this._url + 'allComments.app').pipe(map(resp => resp as IComment[]));
     obs.subscribe(data => this.commentMapper(data));
     // obs.subscribe(data => console.log(data));
     return null;
  }

  getComLikes() {
    this.comLikes = [];

  }

  getPostLikes() {

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
    for (let i = 0; i < this.posts.length; ++i) {
      this.posts[i].comments = [];
      for (let j = 0; j < this.comments.length; ++j) {
        if (this.posts[i].pid === this.comments[j].post.pid) {
            this.posts[i].comments.push(this.comments[j]);
        }
      }
    }
    return null;
  }

  getPostsByUserId(id: number): IPost[] {
    const userPosts = [];
    for (let i = 0; i < this.posts.length; ++i) {
      if (this.posts[i].creator.id === id) {
        userPosts.push(this.posts[i]);
      }
    }
    return userPosts;
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

  createPost(post: IPost) {
    this._httpServ.post(this._url + 'newPost.app', 'body=' + post.body + '&username=' + post.creator.username
      , this.httpOptions).subscribe(data => console.log(data));
  }

  updatePost(post: IPost, user: IUser, d: Boolean) {
    const url = 'http://localhost:9005/starwar/likePost.app';
    this._httpServ.post(url, 'pid=' + post.pid +
     '&username=' + user.username + '&delete=' + d, this.httpOptions).subscribe(data => console.log(data));
  }

  createComment(com: IComment) {
    const url = 'http://localhost:9005/starwar/newComment.app';
    this._httpServ.post(url, 'body=' + com.body + '&postid=' + com.post.pid +
     '&username=' + com.poster.username, this.httpOptions).subscribe(data => console.log(data));
  }

  updateComment(com: IComment, user: IUser, d: Boolean) {
    const url = 'http://localhost:9005/starwar/likeComment.app';
    this._httpServ.post(url, 'cid=' + com.cid +
     '&username=' + user.username + '&delete=' + d, this.httpOptions).subscribe(data => console.log(data));
  }
  getActivePost(): IPost {
    return this.activePost;
  }

  setActivePost(post: IPost) {
    this.activePost = post;
  }


}
