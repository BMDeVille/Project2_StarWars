import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { IUser } from '../db_models/user';
import { IComment } from '../db_models/comment';
import { IPost } from '../db_models/post';
import { ProfileService } from '../services/profile.service';

@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.css']
})
export class InboxComponent implements OnInit {

  newPost: IPost;
  newComment: IComment;
  newFollower: IUser;
  curr_user: IUser;
  newLike: IPost;
  constructor(private _notify: UserService, private _profile: ProfileService) {
    this.curr_user = _profile.getCurrentUser();
  }

  ngOnInit() {
    console.log(this.curr_user);
   }
   setUser(user: IUser) {
    this.curr_user = user;
    this.newComment = this.newComment;
    this.newLike = this.newLike;
    this.newPost = this.newPost;
  }}
