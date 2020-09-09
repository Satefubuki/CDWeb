import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { OrderProduct } from '../models/order-product';
import { Observable } from 'rxjs';
import {ApiService} from './api.service';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private loggedInStatus = JSON.parse(localStorage.getItem('loggedIn') || 'false');

  list = [];
  isExist: boolean = false;
  total: number = 0;

  constructor(private cookieService: CookieService, private apiService: ApiService) {
    
   }

  addLocalCart(op: OrderProduct) {
  //  this.list = JSON.parse(localStorage.getItem('cart'));
    this.isExist = false;
    this.list.forEach(element => {
      if(element.name == op.name){
        element.quantity++;
        element.price = element.price * element.quantity;
        this.isExist = true;
      }
    });
    if(this.isExist == false){
      this.list.push(op);
    }
    console.log(this.list);
    localStorage.setItem('cart', JSON.stringify(this.list));
  }

  addToCart(op: Product, account): Observable<any> {
    console.log(op);
    return this.apiService.post(`${this.apiService.apiUrl.cart.get}?username=${account}`, op);
  }

  getUserProduct(username: string): Observable<any>{
    return this.apiService.getCart(this.apiService.apiUrl.cart.get, username);
  }

  getTotal() {
    this.list = (JSON.parse(localStorage.getItem('cart')) || []);
    this.list.forEach(element => {
      this.total += (element.quantity * element.price);
    });
    console.log(this.total);
    return this.total;
  }

  getLocalCart(){
    this.list = (JSON.parse(localStorage.getItem('cart')) || []);
    return this.list;
  }

  mergeCart(products: OrderProduct[], account: string): Observable<any>{
    return this.apiService.mergeCart(this.apiService.apiUrl.cart.merge, products, account);
  }

  reset(){
    this.list = [];
    localStorage.removeItem('cart');
  }

  delete(id): Observable<any> {
    const url = `${this.apiService.apiUrl.cart.get}/${id}`;
    return this.apiService.delete(url);
  }
}
