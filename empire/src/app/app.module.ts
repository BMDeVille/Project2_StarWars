import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { ProfileComponent } from './profile/profile.component';
import { FeedComponent } from './feed/feed.component';
import { PostComponent } from './post/post.component';
import { InboxComponent } from './inbox/inbox.component';
import { LoginComponent } from './login/login.component';

import { PostService } from './services/post.service';
import { ProfileService } from './services/profile.service';
import { ModalService } from './services/modal.service';
import { DomService } from './services/dom.service';
import { SignupComponent } from './signup/signup.component';
import { UserinformationComponent } from './userinformation/userinformation.component';
import { ChangeinformationComponent } from './changeinformation/changeinformation.component';
import { ImagesComponent } from './images/images.component';
import { SearchComponent } from './search/search.component';
import { LostPassComponent } from './lost-pass/lost-pass.component';

import { UploadService } from './services/upload.service';
import { SuccessfulUploadComponent } from './successful-upload/successful-upload.component';
import { ResetPassComponent } from './reset-pass/reset-pass.component';

@NgModule({
  declarations: [
    AppComponent,
    ProfileComponent,
    FeedComponent,
    LoginComponent,
    PostComponent,
    InboxComponent,
    SignupComponent,
    UserinformationComponent,
    ChangeinformationComponent,
    ImagesComponent,
    SearchComponent,
    LostPassComponent,
    SuccessfulUploadComponent,
    ResetPassComponent
  ],
  imports: [
    BrowserModule, FormsModule, ReactiveFormsModule, RouterModule.forRoot([
      {path: '', redirectTo: 'feed', pathMatch: 'full'},
      {path: 'feed', component: FeedComponent},
      {path: 'profile', component: ProfileComponent},
      {path: 'inbox', component: InboxComponent},
      {path: 'search', component: SearchComponent},
       {path: 'login', component: LoginComponent},
       {path: 'reset/:username', component: ResetPassComponent}
    ]),
     HttpClientModule,
  ],
  entryComponents: [
    LoginComponent,
    SignupComponent,
    UserinformationComponent,
    PostComponent,
    ChangeinformationComponent,
    ImagesComponent,
    LostPassComponent
  ],
  providers: [PostService, ProfileService, ModalService, DomService],
  bootstrap: [AppComponent]
})
export class AppModule { }
