import { Component, OnInit } from '@angular/core';
import { ModalService } from '../services/modal.service';
import { ProfileService } from '../services/profile.service';
import { IUser } from '../db_models/user';
import { IPost } from '../db_models/post';
import { PostService } from '../services/post.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  activeUser: IUser;
  newPost: IPost;
  files: FileList;
  constructor(private _modalService: ModalService, private userService: ProfileService, private postService: PostService) {
    this.activeUser = userService.getCurrentUser();
  }

  ngOnInit() {
  }

  public close() {
    this._modalService.destroy();
  }

  fileNameChanged(event: { target: HTMLInputElement; }) {
    const filename = (<HTMLInputElement>document.getElementById('image')).value.split('\\');
    (<HTMLInputElement>document.getElementById('filepath')).value = filename[filename.length - 1];
  }

  public createPost() {
    const body = (<HTMLTextAreaElement>document.getElementById('body')).value;
    const youtube = (<HTMLInputElement>document.getElementById('youtube')).value;
    this.files = (<HTMLInputElement>document.getElementById('body')).files;
    console.log(this.files); // files is undefined
    // need to decide how to send fileList to back end with IImage
    const newImage = {'iid': 1, 'image': ''};
    this.newPost = {'pid': 1, 'body': body, 'youtube': youtube, 'created': new Date() ,
    'creator' : this.activeUser, 'comments': null, 'likes': null, 'images': [newImage]};
    this.postService.createPost(this.newPost);
    this._modalService.destroy();
  }
}

