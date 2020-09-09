import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormGroup, FormBuilder, Validators, EmailValidator } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { User } from 'src/app/models/User';
import { CookieService } from 'ngx-cookie-service';
import { PnotifyService } from '../../utils/pnotify.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
  encapsulation: ViewEncapsulation.None,

})
export class ProfileComponent implements OnInit {
  profileForm: FormGroup;
  user: User;
  account: string;

  constructor( private fb: FormBuilder, private userService: UserService, private cookieService: CookieService,
               private pnotifyService: PnotifyService) {
    this.profileForm = this.fb.group({
      name: ['', Validators.required, ],
      email: ['', Validators.required, ],
      phone: ['', [Validators.required, Validators.minLength(9), Validators.maxLength(12)]],
      address: ['', Validators.required],
      });
   }

  ngOnInit() {
    this.account = this.cookieService.get('account');
    this.userService.check(this.account).subscribe(res => {
      this.user = res.data;
      console.log(this.user);
    });
  }

  update() {
    console.log(this.user);
    this.userService.save(this.user).subscribe(res => {
      console.log(res);
      this.pnotifyService.success('Thông báo', 'Cập nhật thành công');
    })
  }
}
