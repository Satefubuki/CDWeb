import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {UserService} from './../../services/user.service';
import {ActivatedRoute, Router} from "@angular/router";
import {Role} from './../../enum/Role';
import { CookieService } from 'ngx-cookie-service';
import {AuthService} from './../../services/auth.service'; 


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  username: string;
  password: string;
  isfail = false;
  constructor( private fb: FormBuilder, private userService: UserService,  private router: Router,
               private authService: AuthService, private cookieService: CookieService) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required, ],
      password: ['', Validators.required, ],
      });
   }

  ngOnInit() {
  }

  login(){
   this.userService.login(this.username, this.password).subscribe(res =>{
    console.log(res);
    if(res.errorCode === 0) {
      this.cookieService.set('token', res.data.token);
      this.cookieService.set('account', res.data.account);
      this.cookieService.set('role', res.data.role);

      this.authService.setLoggedIn(true);

      if(res.data.role == Role.Manager) {
        this.router.navigateByUrl('/admin');
      } else if(res.data.role == Role.Customer) {
        this.router.navigateByUrl('/');
      }
    } else {
      this.isfail = true;
    }

   })
   
  }
}
