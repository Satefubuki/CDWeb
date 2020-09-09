import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';
import { HttpClient, HttpRequest, HttpHeaders, HttpEvent } from '@angular/common/http';
import { User } from '../models/User';



@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private apiService: ApiService, private http: HttpClient) { }

  get(): Observable<any> {
    return this.apiService.get<any>(this.apiService.apiUrl.user.register);
}

  check(username: string): Observable<any> {
    return this.apiService.getUsername<any>(this.apiService.apiUrl.user.check, username);
  }

  save(data: User): Observable<any> {
    if (data.id === 0) {
      return this.apiService.post<any>(this.apiService.apiUrl.user.register, data);
    }
    if (data.id !== 0) {
      return this.apiService.put<any>(this.apiService.apiUrl.user.update, data);
    }
  }

  login(username: string, password: string): Observable<any> {
    const data = {
      // tslint:disable-next-line: object-literal-shorthand
      username: username,
      // tslint:disable-next-line: object-literal-shorthand
      password: password
      };
    return this.apiService.post<any>(this.apiService.apiUrl.user.login, data);
    }
  }

