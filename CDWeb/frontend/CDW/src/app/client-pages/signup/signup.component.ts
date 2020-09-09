import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {ActivatedRoute, Router} from "@angular/router";
import { User } from 'src/app/models/User';
import { Role } from 'src/app/enum/Role';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  user: User;
  username: string;
  password: string;
  cpassword: string;
  role: string = Role.Customer;
  isExist = false;
  isCorrect = true;
  signupForm: FormGroup;


  constructor(private userService: UserService, private fb: FormBuilder, private router: Router) {
    this.signupForm = this.fb.group({
      username: ['', Validators.required, ],
      password: ['', Validators.required, ],
      cpassword: ['', Validators.required, ],
      });
   }

  ngOnInit() {

  }

  checkuser(event: any){
   // this.username = event.target.value;
    this.userService.check(this.username).subscribe(res =>{
      if (res.errorCode !== 0) {
        this.isExist = true;
      } else {
        this.isExist = false;
      }
    }
      );
  }
  checkpass(event: any){
    if (this.password !== this.cpassword) {
      this.isCorrect = false;
    } else {
      this.isCorrect = true;
    }
  }
  register(){
    this.user = {id: 0, userName: this.username, password: this.password,
       address: null, email: null, fullName: null, phoneNumber: null, role: this.role };
    this.userService.save(this.user).subscribe(res => {
      alert('Đăng kí thành công');
      this.router.navigateByUrl('/login');
       })

  }

}
