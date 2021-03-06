import { AfterViewInit, Component, OnInit } from '@angular/core';
import { LoginComponent } from '../login/login.component';
import { ModalService } from '../services/modal.service';
import { UserService } from '../services/user.service';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit, AfterViewInit {

  // create an object that recevied any type of input
  isValidFormSubmitted = null;
  reg: any = {};
  theType: any = {};

  regForm: FormGroup;

  constructor(private _modalService: ModalService, private _userService: UserService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.regForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      DOB: ['', Validators.required],
      // type: ['', Validators.required],
      // sec_ques: ['', Validators.required],
      sec_ans: ['', Validators.required]
    });
  }

  onFormSubmit() {
    this.isValidFormSubmitted = false;
    if (this.regForm.invalid) {
      return;
    }
    this.isValidFormSubmitted = true;
    // console.log('form' + this.regForm.value);
    this.reg = this.regForm.value;
    this.reg.type = this.theType.type;
    this.reg.sec_ques = this.theType.sec_ques;
    // console.log(this.reg);
    this._userService.regUser(this.reg).subscribe();
    this.regForm.reset();
  }

  ngAfterViewInit() {
  }

  initLoginModal() {
    const inputs = {
      isMobile: false
    };
    this._modalService.reInit(LoginComponent, inputs, {});
  }

  selectType(event: any) {
    this.theType.type = event.target.value;
  }
  selectSecurity(event: any) {
    this.theType.sec_ques = event.target.value;
  }
  // when click register button
  // register() {
  //   // call user service and send register information
  //   this._userService.regUser(this.reg).subscribe(data => console.log(data));
  //   console.log(this.reg);
  //   this.initLoginModal();
  // }
}
