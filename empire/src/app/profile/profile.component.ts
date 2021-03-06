
import {Component, OnInit, ElementRef, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { ModalService } from '../services/modal.service';
import { UserinformationComponent } from '../userinformation/userinformation.component';
import { PostService } from '../services/post.service';

import { IPost } from '../db_models/post';
import { IComment } from '../db_models/comment';
import { PostComponent } from '../post/post.component';
import { ProfileService } from '../services/profile.service';
import { IUser } from '../db_models/user';
import { UploadService } from '../services/upload.service';
import { SuccessfulUploadComponent } from '../successful-upload/successful-upload.component';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})

export class ProfileComponent implements OnInit {
  form: FormGroup;
  loading: boolean;
  showImageChange: boolean;
  posts: IPost[] = [];
  cp: IPost;
  toggleFlag: boolean;
  activeUser: IUser;
  viewUser: IUser;
  imageSrc: string;
  selectedFiles: FileList;
  pic: any = {};
  linkfirsthalf = 'https://qconlon.s3.amazonaws.com/profileimage/';


  @ViewChild('fileInput') fileInput: ElementRef;
  constructor(private fb: FormBuilder,  private _modalService: ModalService,  _postservice: PostService,
    private _profileService: ProfileService, private _upload: UploadService, private _userService: UserService) {
    this.createForm();
    this.showImageChange = false;
    // this.posts = _postservice.getFeed();
    this.toggleFlag = false;
    // this.activeUser = _profileService.getCurrentUser();
    this.activeUser = JSON.parse(localStorage.getItem('currentUser'));
   // this.viewUser = _profileService.getViewUser();
   if (JSON.parse(localStorage.getItem('viewUser')) === null) {
    this.viewUser = this._profileService.getViewUser();
   } else {
    this.viewUser = JSON.parse(localStorage.getItem('viewUser'));
   }
    if (this.viewUser.image == null) {
      this.viewUser.image = 'assets/images/1.jpg';
    }
  }

  initAboutModal() {
    const inputs = {
      isMobile: false,
    };
    this._modalService.init(UserinformationComponent, inputs, {});
  }

  createForm() {
    this.form = this.fb.group({
      // name: ['', Validators.required],
      profile: ['', Validators.required]
    });
  }



  showOption() {
    this.showImageChange = true;
  }

  hideOption() {
    this.showImageChange = false;
  }

  ngOnInit() {
  }

  initNewPostModal() {
    const inputs = {
      isMobile: false
    };
    this._modalService.init(PostComponent, inputs, {});
  }
  likeComment(c: IComment) {

  }

upload() {
  const file = this.selectedFiles.item(0);
  this._upload.uploadfile(file);
//   console.log('upload');
//   console.log(file);
//  console.log(image);
  this.pic.location = this.linkfirsthalf + file.name;
    this._userService.updateProfilePicture(this.pic);



  const inputs = {
    isMobile: false
  };
  this._modalService.init(SuccessfulUploadComponent, inputs, {});
}

selectFile(event) {
  {
  this.selectedFiles = event.target.files;
}

}
}

  // onSubmit() {
  //   const formModel = this.form.value;
  //   this.loading = true;
  //   // In a real-world app you'd have a http request / service call here like
  //   // this.http.post('apiUrl', formModel)
  //   setTimeout(() => {
  //     console.log(formModel);
  //     alert('Your image has been uploaded.');
  //     this.loading = false;
  //   }, 1000);
  //   this.fileInput.nativeElement.value = '';

  // }

  // clearFile() {
  //   this.form.get('profile').setValue(null);
  //   this.fileInput.nativeElement.value = '';
  // }

  // onFileChange(event) {
  //   const read = new FileReader();
  //   if (event.target.files && event.target.files.length > 0) {
  //     const file = event.target.files[0];
  //     read.readAsDataURL(file);
  //     read.onload = () => {
  //       this.form.get('profile').setValue({
  //         filename: file.name,
  //         filetype: file.type,
  //         value: read.result.split(',')[1]
  //       });
  //     };
  //   }
  // }
