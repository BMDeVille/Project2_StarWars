import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '../../../node_modules/@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-reset-pass',
  templateUrl: './reset-pass.component.html',
  styleUrls: ['./reset-pass.component.css']
})
export class ResetPassComponent implements OnInit {
  username: string;
  sec_ans: string;
  new_pass: string;
  constructor(private route: ActivatedRoute, private _userService: UserService) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.username = params.get('username');
    });
  }

  retTrue() {
    return true;
  }

  reset() {
    this._userService.resetPassword(this.username, this.new_pass, this.sec_ans);
  }
}
