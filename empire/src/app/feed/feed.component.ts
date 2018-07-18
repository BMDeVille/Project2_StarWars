import { Component, OnInit, AfterViewInit} from '@angular/core';

import { ModalService } from '../services/modal.service';
import { LoginComponent } from '../login/login.component';
import { PostService } from '../services/post.service';
import { IPost } from '../db_models/post';
import { IComment } from '../db_models/comment';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit, AfterViewInit {

  posts: IPost[] = [];
  cp: IPost;
  toggleFlag: boolean;
  constructor(private _modalService: ModalService, _postservice: PostService) {
    this.posts = _postservice.getFeed('');
    this.toggleFlag = false;
  }

  ngOnInit() {
  }
  ngAfterViewInit() {
    setTimeout(_ => this.initLoginModal());
  }

   initLoginModal() {
    const inputs = {
      isMobile: false
    };
    this._modalService.init(LoginComponent, inputs, {});
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
    ul.className = 'feed';
    for (let i = 0; i < this.cp.comments.length; ++i) {
      const li = document.createElement('li');
      li.innerHTML = this.cp.comments[i].body
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
    c.likes.push({'id': 1, 'fname': 'Darth', 'lname': 'Vador', 'username': 'SithLord', 'about': '',
    'sec_ans': '', 'dob': new Date(), 'allegiance': 1, 'email': 'd.vador@empire.gov', 'followers': null, 'posts': null, 'image': 1});
  }
}
