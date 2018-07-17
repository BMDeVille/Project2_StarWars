import { Component, OnInit, AfterViewInit} from '@angular/core';

import { ModalService } from '../services/modal.service';
import { LoginComponent } from '../login/login.component';
import { PostService } from '../services/post.service';
import { IPost } from '../db_models/post';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit, AfterViewInit {

  posts: IPost[] = [];
  constructor(private _modalService: ModalService, _postservice: PostService) { 
    this.posts = _postservice.getFeed('');
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

}
