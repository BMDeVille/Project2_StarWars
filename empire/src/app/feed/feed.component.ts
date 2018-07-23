import { Component, OnInit} from '@angular/core';

import { ModalService } from '../services/modal.service';
import { PostService } from '../services/post.service';
import { IPost } from '../db_models/post';
import { IComment } from '../db_models/comment';
import { PostComponent } from '../post/post.component';
import { IUser } from '../db_models/user';
import { ProfileService } from '../services/profile.service';
import { Router } from '@angular/router';
import { ImagesComponent } from '../images/images.component';
import { ImageService } from '../services/image.service';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit {

  posts: IPost[] = [];
  cp: IPost;
  toggleFlag: boolean;
  activeUser: IUser;
  constructor(private _modalService: ModalService, private _postservice: PostService, private _profileService: ProfileService,
    private router: Router, private _imageService: ImageService) {
    this.posts = _postservice.getFeed('');
    this.toggleFlag = false;
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

  displayImages(event: { target: HTMLInputElement; }) {
    const postId = +event.target.parentElement.id + 1;
    console.log(postId);
    this._imageService.setImages(this._postservice.getPostById(postId).images);
    const inputs = {
      isMobile: false
    };
    this._modalService.init(ImagesComponent, inputs, {});
  }
  setViewUser() {
    console.log((<HTMLElement>event.target).parentElement.id);
    // get post by id to get user
    const post = this._postservice.getPostById(+(<HTMLElement>event.target).parentElement.id);
    console.log(post);
    // assign user to viewUser
    this._profileService.setViewUser(post.owner);
    console.log(post.owner);
    this.router.navigateByUrl('/profile');
  }

  toggleComments(event: { target: HTMLInputElement; }) {
    console.log(event.target.parentElement.parentElement.children);
    const coms = (<HTMLUListElement>event.target.parentElement.parentElement.children[1]);
    if (coms.style.display === 'none') {
      coms.style.display = 'block';
    } else {
      coms.style.display = 'none';
    }
  }

  likeComment(event: {target: HTMLInputElement}) {
    console.log(event.target.parentElement.parentElement);
    const commentId = +event.target.parentElement.parentElement.id;
    console.log(event.target.parentElement.parentElement.parentElement.parentElement.children[0]);
    const postId = +event.target.parentElement.parentElement.parentElement.parentElement.children[0].id;
    const com = this._postservice.getCommentByIdAndPostId(commentId, postId);
    let found = false;
    let newlikes;
    // check if likes
    if (com.likes != null) {
      newlikes = com.likes;
      for ( let i = 0; i < newlikes.length; ++i) {
        if (this.activeUser.id === newlikes[i].id) {
          // remove like
          newlikes.splice(newlikes[i].id);
          found = true;
          break;
        }
      }
      if (!found) {
        // add like
        newlikes.push(this.activeUser);
      }
    // first like
    } else {
      newlikes = [this.activeUser];
    }
    // update likes
    com.likes = newlikes;
    this._postservice.updateComment(com);
    // now need to refresh component
  }

  likePost(event: { target: HTMLInputElement; }) {
    let found = false;
    const postId = +event.target.parentElement.id;
    const clickedPost = this._postservice.getPostById(postId);
    let newlikes;
    // check if likes
    if (clickedPost.likes != null) {
      newlikes = clickedPost.likes;
      for ( let i = 0; i < newlikes.length; ++i) {
        if (this.activeUser.id === newlikes[i].id) {
          // remove like
          newlikes.splice(newlikes[i].id);
          found = true;
          break;
        }
      }
      if (!found) {
        // add like
        newlikes.push(this.activeUser);
      }
    // first like
    } else {
      newlikes = [this.activeUser];
    }
    // update likes
    clickedPost.likes = newlikes;
    this._postservice.updatePost(clickedPost);
  }
  // now need to refresh component

}
