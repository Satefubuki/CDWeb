import { Component, OnInit } from '@angular/core';
import { Paging } from './../../models/paging';
import { OrderService } from './../../services/order.service';
import { Order } from './../../models/order';
import { User } from './../../models/User';
import { PnotifyService } from '../../utils/pnotify.service';
import { CookieService } from 'ngx-cookie-service';
import { UserService } from '../../services/user.service';


@Component({
  selector: 'app-order-admin',
  templateUrl: './order-admin.component.html',
  styleUrls: ['./order-admin.component.css'] 
})
export class OrderAdminComponent implements OnInit {
  paging = { page: 0, pageLimit: 5, totalItems: 3 } as Paging;
  orders: Order[] = [];
  account: string;
  user: User;

  constructor(private orderServicce: OrderService, private pnotifyService: PnotifyService,
              private cookieService: CookieService, private userService: UserService ) { }

  ngOnInit() {
    this.account = this.cookieService.get('account');
    this.userService.check(this.account).subscribe(res => {
      this.user = res.data;
      console.log(this.user);
      
    })
    this.loadOrder(null);
  }

  loadOrder(page){
    if (page != null) {
      this.paging.page = page.offset;
    }
    this.orderServicce.getAllInPage(this.paging.page, this.paging.pageLimit).subscribe(res => {
      this.orders = res.content;
      console.log(this.orders);
      this.paging.page = res.number;
      this.paging.pageLimit = res.size;
      this.paging.totalItems = res.totalElements;
      this.paging.totalPages = res.totalPages;
    });
  }

  accept(order: Order) {
    order.orderStatus = 1;
    this.orderServicce.changeStatus(order, this.user.email).subscribe(res => {
      this.pnotifyService.success('Thông báo', 'Đã xử lý đơn hàng');
      this.loadOrder(null);
    });
  }

  decline(order: Order) {
    order.orderStatus = 2;
    this.orderServicce.changeStatus(order, this.user.email).subscribe(res => {
      this.pnotifyService.success('Thông báo', 'Đã từ chối đơn hàng');
      this.loadOrder(null);
    });
  }

  status(code){
    switch (code) {
      case 0:
        return 'Đang xử lý';
        break;
      case 1:
        return 'Chấp nhận';
        break;
      case 2:
        return 'Từ chối';
        break;
    }
  }

}
