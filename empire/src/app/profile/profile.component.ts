
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

  @ViewChild('fileInput') fileInput: ElementRef;
  constructor(private fb: FormBuilder,  private _modalService: ModalService,  _postservice: PostService,
    private _userService: ProfileService) {
    this.createForm();
    this.showImageChange = false;
    this.posts = _postservice.getFeed('');
    this.toggleFlag = false;
    console.log(_userService.curr_user);
    this.activeUser = _userService.curr_user;
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

  onSubmit() {
    const formModel = this.form.value;
    this.loading = true;
    // In a real-world app you'd have a http request / service call here like
    // this.http.post('apiUrl', formModel)
    setTimeout(() => {
      console.log(formModel);
      alert('Your image has been uploaded.');
      this.loading = false;
    }, 1000);
    this.fileInput.nativeElement.value = '';

  }

  clearFile() {
    this.form.get('profile').setValue(null);
    this.fileInput.nativeElement.value = '';
  }

  onFileChange(event) {
    const read = new FileReader();
    if (event.target.files && event.target.files.length > 0) {
      const file = event.target.files[0];
      read.readAsDataURL(file);
      read.onload = () => {
        this.form.get('profile').setValue({
          filename: file.name,
          filetype: file.type,
          value: read.result.split(',')[1]
        });
      };
    }
  }

  ngOnInit() {
  }

  initNewPostModal() {
    const inputs = {
      isMobile: false
    };
    this._modalService.init(PostComponent, inputs, {});
  }

  // toggleComments(event: { target: HTMLInputElement; }) {
  //   if (!this.toggleFlag) {
  //   const commentId = +event.target.parentElement.id; // + casts to number
  //   for (let i = 0; i < this.posts.length; ++i) {
  //       if (this.posts[i].pid === commentId) {
  //         this.cp = this.posts[i];
  //         break;
  //       }
  //   }
  //   const ul = document.createElement('ul');
  //   // ul.className = 'Feed';
  //   for (let i = 0; i < this.cp.comments.length; ++i) {
  //     const li = document.createElement('li');
  //     li.innerHTML = '<< ' + this.cp.comments[i].body
  //      + '<button style="border: none; background-color: black" (click)="likeComment">'
  //      + '<img src = "assets/images/1485477009-like_78561.png" width = "30px" height = "30px"></button>['
  //       + (this.cp.comments[i].likes != null ? this.cp.comments[i].likes.length : 0 ) + ']';
  //       ul.appendChild(li);
  //   }
  //   event.target.parentElement.parentElement.appendChild(ul);
  // } else {
  //   event.target.parentElement.parentElement.removeChild(event.target.parentElement.nextSibling);
  // }
  // this.toggleFlag = !this.toggleFlag;
  // }

  likeComment(c: IComment) {

  }
}
