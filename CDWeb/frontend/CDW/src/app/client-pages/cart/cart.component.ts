import { Component, OnInit, ViewEncapsulation  } from '@angular/core';
import { CartService } from './../../services/cart.service';
import { PnotifyService } from './../../utils/pnotify.service';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { OrderService } from '../../services/order.service';
import { UserService } from '../../services/user.service';
import { User } from '../../models/User';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class CartComponent implements OnInit {

  products = [];
  cartTotal = 0;
  account: string;
  token: string;
  user: User;
  private loggedInStatus = JSON.parse(localStorage.getItem('loggedIn') || 'false');
  constructor(private cartService: CartService, private pnotifyService: PnotifyService, private route: Router,
              private cookieService: CookieService, private orderService: OrderService, private userService: UserService) { }

  ngOnInit() {
    console.log('----------------' +  this.loggedInStatus);
    this.token = this.cookieService.get('token');
    if (!this.token) {
        this.products = (JSON.parse(localStorage.getItem('cart')) || []);
        console.log(this.products);
      } else {
        this.products = (JSON.parse(localStorage.getItem('cart')) || []);
        this.account = this.cookieService.get('account');
        this.cartService.mergeCart(this.products, this.account).subscribe(res => {
          console.log(res.errorCode);
          this.cartService.reset();
          this.cartService.getUserProduct(this.account).subscribe(res => {
            console.log(res);
            this.products = res.data;
          });
        });
        this.countAll();
      }
    setTimeout(() => { this.countAll();} );
    this.cartTotal = 0;

  }

  itemPrice(price, quantity) {
    return price * quantity;
  }

  countAll() {
    for (let index = 0; index < this.products.length; index++) {
      const element = this.products[index];
      this.cartTotal += (element.price * element.quantity);
      console.log(this.cartTotal);
    }
  }

  loadUserProduct() {
    this.cartService.getUserProduct(this.account).subscribe(res => {
      this.products = res.data;
    });
  }

  delete(id) {
    this.cartService.delete(id).subscribe(res => {
      this.pnotifyService.success('Thông báo', 'xóa thành công');
      this.cartService.getUserProduct(this.account).subscribe( res => {
        console.log(res);
        this.products = res.data;
      });
    });
  }

  pay() {
    if (this.loggedInStatus == false) {
      this.pnotifyService.showConfirm('Thông báo', 'Bạn chưa đăng nhập, vui lòng đăng nhập để tiếp tục', yes => {
        if (yes) {
          this.route.navigate(['/login']);
       } });
      }
    if (this.loggedInStatus) {
      this.userService.check(this.account).subscribe( (res) => {
        this.user = res.data;
        console.log(this.user);
        if (!this.user.email && !this.user.address) {
          this.pnotifyService.showConfirm('Thông báo', 'bạn cần cập nhật thông tin để tiếp tục', yes => {
            if (yes) {
              this.route.navigate(['/profile']);
          } });
      } else {
        this.orderService.addOrder(this.products, this.account).subscribe(res => {
          this.pnotifyService.success('Thông báo', 'Đặt hàng thành công');
          this.loadUserProduct();
          this.countAll();
        });
      }
      });
    }
    }
  }
