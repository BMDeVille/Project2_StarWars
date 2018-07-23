import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { IUser } from '../db_models/user';
import { IComment } from '../db_models/comment';
import { IPost } from '../db_models/post';

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
  constructor(private _notify: UserService) { }

  ngOnInit() {
  }


  // showNotify() {
  //   this._notify.getUser();
  // }
}
