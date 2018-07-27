import { Component, OnInit } from '@angular/core';
import { ModalService } from '../services/modal.service';
import { ProfileService } from '../services/profile.service';
import { IUser } from '../db_models/user';
import { IPost } from '../db_models/post';
import { PostService } from '../services/post.service';
import { UploadService } from '../services/upload.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  activeUser: IUser;
  newPost: IPost;
  files: FileList;
  pic: any = {};
  linkfirsthalf = 'https://qconlon.s3.amazonaws.com/profileimage/';
  constructor(private _modalService: ModalService, private userService: ProfileService,
    private postService: PostService, private _upload: UploadService, private _userService: UserService) {
    // this.activeUser = userService.getCurrentUser();
    this.activeUser = JSON.parse(localStorage.getItem('currentUser'));
    // console.log('in post comp');
    // console.log(this.activeUser.email);
  }

  ngOnInit() {
  }

  public close() {
    this._modalService.destroy();
  }

  upload() {
    const file = this.files.item(0);
    this._upload.uploadfile(file);
  //   console.log('upload');
  //   console.log(file);
  //  console.log(image);
    this.pic.location = this.linkfirsthalf + file.name;
    
      this._userService.updateProfilePicture(this.pic);
  }

  selectFile(event) {
    {
    this.files = event.target.files;
  }

  }
  fileNameChanged(event: { target: HTMLInputElement; }) {
    const filename = (<HTMLInputElement>document.getElementById('image')).value.split('\\');
    (<HTMLInputElement>document.getElementById('filepath')).value = filename[filename.length - 1];
  }

  public createPost(pic: any) {
    const body = (<HTMLTextAreaElement>document.getElementById('body')).value;
    const youtube = (<HTMLInputElement>document.getElementById('youtube')).value;
    this.files = (<HTMLInputElement>document.getElementById('body')).files;
    const img = pic.location;
    // console.log(this.files); // files is undefined
    // need to decide how to send fileList to back end with IImage
    const newImage = '';
    this.newPost = {'pid': 1, 'body': body, 'youtube': youtube, 'created': new Date() ,
    'creator' : this.activeUser, 'comments': null, 'likes': null, 'images': img};
    this.postService.createPost(this.newPost);
    this._modalService.destroy();
  }
}

