import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Product } from 'src/app/models/product';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService} from './../../services/product.service';
import { CookieService } from 'ngx-cookie-service';
import { CartService } from './../../services/cart.service';
import { OrderProduct } from 'src/app/models/order-product';
import { PnotifyService } from './../../utils/pnotify.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css'],
  encapsulation: ViewEncapsulation.None,

})
export class DetailComponent implements OnInit {
  product: Product;
  oproduct: OrderProduct;
  selectedImg: string;
  actPrice: number = 0;
  
  constructor(private productService: ProductService, private route: ActivatedRoute,
              private cookieService: CookieService, private cartService: CartService,
              private pnotifyService: PnotifyService) { }

  ngOnInit() {
    const productId: number = Number(this.route.snapshot.paramMap.get('id'));
    this.productService.getOne(productId).subscribe(res =>{
      this.product = res.data;
      this.selectedImg = `http://localhost:8081/files/${this.product.mainImg}`;
    });
  }

  changeImg(event){
    this.selectedImg = event.toElement.currentSrc;
  }

  addCart() {
    const token = this.cookieService.get('token');
    this.actPrice = Math.ceil(this.product.price - this.product.price * this.product.sale / 100);
    //chưa đăng nhập
    if (!token) {
      this.oproduct = {id: 0, brand: this.product.brand, color: this.product.color, img: this.product.mainImg
                      , name: this.product.name, price: this.actPrice, type: this.product.type, quantity: 1};
      console.log(this.oproduct);
      this.cartService.addLocalCart(this.oproduct);
    }
    if (token) {
      const account = this.cookieService.get('account');
      console.log(account);
      console.log(this.product);
      
      this.cartService.addToCart(this.product , account ).subscribe(res =>{
        console.log(res.data);

      });
    }
    this.pnotifyService.success('Thông báo', 'Thêm ' + this.product.name + ' vào giỏ thành công');
  }
}
