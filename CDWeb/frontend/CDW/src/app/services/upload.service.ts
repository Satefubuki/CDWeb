import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpHeaders, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Product} from './../models/product';

@Injectable({
  providedIn: 'root'
})
export class UploadService {
  private baseUrl = 'http://localhost:8081';

  constructor(private http: HttpClient) { }

  upload(file: File): Observable<HttpEvent<any>> {
   
    const formData: FormData = new FormData();
    formData.append('file', file);
    //formData.append('name', data.name);

    const req = new HttpRequest('POST', `${this.baseUrl}/upload`, formData);

    return this.http.request(req);
  }

  getFiles(): Observable<any> {
    return this.http.get(`${this.baseUrl}/files`);
  }
}