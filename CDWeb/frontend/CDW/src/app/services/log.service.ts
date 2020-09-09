import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';
import { Log } from '../models/log';


@Injectable({
  providedIn: 'root'
})
export class LogService {

  constructor(private apiService: ApiService) { }

  getAllInPage(page: number, size: number): Observable<any> {
    const url = `${this.apiService.apiUrl.log.get}?page=${page + 1}&size=${size}`;
    return this.apiService.get(url);
  }

  saveLog(log: Log): Observable<any> {
    console.log(log);
    
    return this.apiService.post(this.apiService.apiUrl.log.get, log);
  }
}
