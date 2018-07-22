import { Component, OnInit} from '@angular/core';

import { ModalService } from '../services/modal.service';
import { PostService } from '../services/post.service';
import { IPost } from '../db_models/post';
import { IComment } from '../db_models/comment';
import { PostComponent } from '../post/post.component';
import { IUser } from '../db_models/user';
import { ProfileService } from '../services/profile.service';

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
  constructor(private _modalService: ModalService, private _postservice: PostService, private _profileService: ProfileService) {
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

  toggleComments(event: { target: HTMLInputElement; }) {
    if (!this.toggleFlag) {
    const commentId = +event.target.parentElement.id; // + casts to number
    for (let i = 0; i < this.posts.length; ++i) {
        if (this.posts[i].pid === commentId) {
          this.cp = this.posts[i];
          break;
        }
    }
    const ul = document.createElement('ul');
    // ul.className = 'Feed';
    for (let i = 0; i < this.cp.comments.length; ++i) {
      const li = document.createElement('li');
      li.innerHTML = '<< ' + this.cp.comments[i].body
       + '<button style="border: none; background-color: black" (click)="likeComment">'
       + '<img src = "assets/images/1485477009-like_78561.png" width = "30px" height = "30px"></button>['
        + (this.cp.comments[i].likes != null ? this.cp.comments[i].likes.length : 0 ) + ']';
        ul.appendChild(li);
    }
    event.target.parentElement.parentElement.appendChild(ul);
  } else {
    event.target.parentElement.parentElement.removeChild(event.target.parentElement.nextSibling);
  }
  this.toggleFlag = !this.toggleFlag;
  }

  likeComment(c: IComment) {

  }
}
