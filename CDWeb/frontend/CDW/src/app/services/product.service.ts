import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpHeaders, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Product } from '../models/product';
import { CookieService } from 'ngx-cookie-service';



@Injectable({
  providedIn: 'root'
})
export class ProductService {

  selectedFiles: FileList;
  currentFile: File;
  progress = 0;
  message = '';
  headers: any;
  constructor(private apiService: ApiService, private http: HttpClient, private cookieService: CookieService) {
    const token = this.cookieService.get('token');
    this.headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

  addProduct(file: File, file2: File, file3: File, data: Product): Observable<any> {
    console.log(data);
    const formData: FormData = new FormData();
    if (file.size > 0) {
      formData.append('file', file);
    }
    if (file2.size > 100) {
      formData.append('file2', file2);
    }
    if (file3.size > 100) {
      formData.append('file3', file3);
    }
    formData.append('name', data.name);
    formData.append('price', data.price + '');
    formData.append('color', data.color);
    formData.append('brand', data.brand.id + '');
    formData.append('stock', data.stock + '');
    formData.append('sale', data.sale + '');
    formData.append('type', data.type.id + '');


    const req = new HttpRequest('POST', this.apiService.apiUrl.product.list, formData, { headers: this.headers });

    return this.http.request(req);
  }

  getAllInPage(page: number, size: number): Observable<any> {
    const url = `${this.apiService.apiUrl.product.list}?page=${page + 1}&size=${size}`;
    return this.apiService.get(url);
  }

  getAllInType(id: number, page: number, size: number): Observable<any> {
    // const {listByType="", listByBrand =""} = this.apiService.apiUrl.product;
    // const searchConditon = listByType || listByBrand;
    const url = `${this.apiService.apiUrl.product.listByType}/${id}?page=${page + 1}&size=${size}`;
    // const urlBrand =`${this.apiService.apiUrl.product.listByType}/${id}?page=${page + 1}&size=${size}`;
    // const url =  encodeURI(`${searchConditon}/${id}?page=${page + 1}&size=${size}`);
    return this.apiService.get(url);

  }
  getAllInBrand(id: number, page: number, size: number): Observable<any> {
    const url = `${this.apiService.apiUrl.product.listByBrand}/${id}?page=${page + 1}&size=${size}`;
    return this.apiService.get(url);
  }


  getOne(id): Observable<any> {
    const url = `${this.apiService.apiUrl.product.list}/${id}`;
    return this.apiService.get(url);
  }

  search(name): Observable<any> {
    return this.apiService.searchProduct(this.apiService.apiUrl.product.search, name);
  }

  delete(id): Observable<any> {
    const url = `${this.apiService.apiUrl.product.list}/${id}`;
    return this.apiService.delete(url);
  }

  editProduct(file: File, file2: File, file3: File, data: Product, id: Number): Observable<HttpEvent<any>> {
    //  const blob = new Blob([JSON.stringify(data)], {type : 'application/json'});
    const formData: FormData = new FormData();
    if (file.size > 100) {
      formData.append('file', file);
    }
    if (file2.size > 100) {
      formData.append('file2', file2);
    }
    if (file3.size > 100) {
      formData.append('file3', file3);
    }
    formData.append('name', data.name);
    formData.append('price', data.price + '');
    formData.append('color', data.color);
    formData.append('stock', data.stock + '');
    formData.append('sale', data.sale + '');
    formData.append('brand', data.brand.id + '');
    formData.append('type', data.type.id + '');
    formData.append('main', data.mainImg);
    formData.append('sub', data.subImg);
    formData.append('sub1', data.subImg2);



    const req = new HttpRequest('POST', `${this.apiService.apiUrl.product.edit}/${id}`, formData, { headers: this.headers });

    return this.http.request(req);
  }


}
