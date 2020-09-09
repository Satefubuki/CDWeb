import { Injectable } from '@angular/core';
import {ApiService} from './api.service';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ProductTypeService {

  constructor(private apiService: ApiService) { }

  list(): Observable<any> {
    return this.apiService.get<any>(this.apiService.apiUrl.productType.list);
}
}
