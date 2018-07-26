import { Component, OnInit} from '@angular/core';

import { ModalService } from '../services/modal.service';
import { PostService } from '../services/post.service';
import { IPost } from '../db_models/post';
import { IComment } from '../db_models/comment';
import { PostComponent } from '../post/post.component';
import { IUser } from '../db_models/user';
import { ProfileService } from '../services/profile.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ImagesComponent } from '../images/images.component';
import { ImageService } from '../services/image.service';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit {

  posts: IPost[] = [];
  comments: IComment[];
  cp: IPost;
  activeUser: IUser;
  constructor(private _modalService: ModalService, private _postservice: PostService, private _profileService: ProfileService,
    private router: Router, private _imageService: ImageService, private _activeRoute: ActivatedRoute) {
    const uri = this.router.url;
    console.log(uri);
    if (uri === '/feed') {
      this.posts = _postservice.getFeed();
    } else {
      this.posts = _postservice.getPostsByUserId(_profileService.getViewUser().id);
    }
    // this.comments = _postservice.getComments();
    this.activeUser = _profileService.getCurrentUser();
  }


  ngOnInit() {
  }

  initNewPostModal() {
    const inputs = {
      isMobile: false
    };
    this._modalService.init(PostComponent, inputs, {});
  }

  submitNewComment(event: { target: HTMLInputElement; }) {
    const body = (<HTMLInputElement>event.target.parentElement.parentElement.children[1]).value;
    const pid = +event.target.parentElement.parentElement.parentElement.id;
    const newCom = {'cid': 1, 'body': body, 'likes': null, 'poster': this._profileService.getCurrentUser(),
     'post': this._postservice.getPostById(pid)};
    this._postservice.createComment(newCom);
  }

  displayImages(event: { target: HTMLInputElement; }) {
    const postId = +event.target.parentElement.id + 1;
    this._imageService.setImages(this._postservice.getPostById(postId).images);
    const inputs = {
      isMobile: false
    };
    this._modalService.init(ImagesComponent, inputs, {});
  }
  setPostViewUser() {
    // get post by id to get user
    const post = this._postservice.getPostById(+(<HTMLElement>event.target).parentElement.id);
    // assign user to viewUser
    this._profileService.setViewUser(post.creator);
    this.router.navigateByUrl('/profile');
  }

  setComViewUser() {
    const com = this._postservice.getCommentById(+(<HTMLElement>event.target).parentElement.id);
    // assign user to viewUser
    this._profileService.setViewUser(com.poster);
    this.router.navigateByUrl('/profile');
  }

  toggleComments(event: { target: HTMLInputElement; }) {
    const coms = (<HTMLUListElement>event.target.parentElement.parentElement.children[1]);
    if (coms.style.display === 'none') {
      coms.style.display = 'block';
    } else {
      coms.style.display = 'none';
    }
  }

  likeComment(event: {target: HTMLInputElement}) {
    const commentId = +event.target.parentElement.parentElement.id;
    const postId = +event.target.parentElement.parentElement.parentElement.parentElement.children[0].id;
    const com = this._postservice.getCommentById(commentId);
    let found = false;
    let newlikes;
    // check if likes
    if (com.likes != null) {
      newlikes = com.likes;
      for ( let i = 0; i < newlikes.length; ++i) {
        if (this._profileService.getCurrentUser().id === newlikes[i].id) {
          // remove like
          newlikes.splice(i, 1);
          found = true;
          break;
        }
      }
      if (!found) {
        // add like
        newlikes.push(this._profileService.getCurrentUser());
      }
    // first like
    } else {
      newlikes = [this._profileService.getCurrentUser()];
    }
    // update likes
    com.likes = newlikes;
    this._postservice.updateComment(com, this._profileService.getCurrentUser(), found);
  }

  likePost(event: { target: HTMLInputElement; }) {
    let found = false;
    const postId = +event.target.parentElement.parentElement.id;
    const clickedPost = this._postservice.getPostById(postId);
    let newlikes;
    // check if likes
    if (clickedPost.likes != null) {
      newlikes = clickedPost.likes;
      for ( let i = 0; i < newlikes.length; ++i) {
        if (this._profileService.getCurrentUser().id === newlikes[i].id) {
          // remove like
          newlikes.splice(i, 1);
          found = true;
          break;
        }
      }
      if (!found) {
        // add like
        newlikes.push(this._profileService.getCurrentUser());
      }
    // first like
    } else {
      newlikes = [this._profileService.getCurrentUser()];
    }
    // update likes
    clickedPost.likes = newlikes;
    this._postservice.updatePost(clickedPost, this._profileService.getCurrentUser(), found);
  }

}
