import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { OrderProduct } from '../models/order-product';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) {
  }
  baseUrl = 'http://localhost:8081/';
  apiUrl = {
    user: {
      check: `${this.baseUrl}user/check`,
      register: `${this.baseUrl}register`,
      login: `${this.baseUrl}login`,
      update: `${this.baseUrl}update`,
    },
    product: {
      list: `${this.baseUrl}product`,
      edit: `${this.baseUrl}product/edit`,
      listByType: `${this.baseUrl}product/type`,
      listByBrand: `${this.baseUrl}product/brand`,
      search: `${this.baseUrl}product/search`
    },
    productType:{
      list: `${this.baseUrl}product-type`,
    },
    brand: {
      list: `${this.baseUrl}product-brand`,
    },
    cart: {
      merge: `${this.baseUrl}cart/merge`,
      get: `${this.baseUrl}cart`
    },
    order: {
      get: `${this.baseUrl}order`,
    },
    log: {
      get:  `${this.baseUrl}log`,
    }
  };

  get<T>(url: string): Observable<T> {
    return this.http.get<T>(url);
  }
  getUsername<T>(url: string, account): Observable<T> {
    return this.http.get<T>(url, {params: {username: account}});
  }
  mergeCart<T>(url: string, data, account): Observable<T> {
    return this.http.post<T>(url, data, {params: {username: account}});
  }
  getCart<T>(url: string, account): Observable<T> {
    return this.http.get<T>(url, {params: {username: account}});
  }
  searchProduct<T>(url: string, data): Observable<T> {
    return this.http.get<T>(url, {params: {name: data}});
  }
  post<T>(url: string, data): Observable<T> {
    return this.http.post<T>(url, data);
  }
  put<T>(url: string, data): Observable<T> {
    return this.http.put<T>(url, data);
  }
  delete<T>(url: string): Observable<T> {
    return this.http.delete<T>(url);
  }
}
