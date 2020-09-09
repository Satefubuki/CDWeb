import { Injectable } from '@angular/core';
import { OrderProduct } from '../models/order-product';
import { Order } from '../models/order';
import { Observable } from 'rxjs';
import {ApiService} from './api.service';

@Injectable({
  providedIn: 'root'
})
export class OrderService { 

  constructor(private apiService: ApiService) { }

  addOrder(products: OrderProduct[], account: string): Observable<any> {
    return this.apiService.post(`${this.apiService.apiUrl.order.get}?username=${account}`, products);
  };

  getAllInPage(page: number, size: number): Observable<any> {
    const url = `${this.apiService.apiUrl.order.get}?page=${page + 1}&size=${size}`;
    return this.apiService.get(url);
  }

  changeStatus(order: Order, email: string): Observable<any> {
    const url = `${this.apiService.apiUrl.order.get}?email=${email}`;
    return this.apiService.put(url, order);
  }
}
