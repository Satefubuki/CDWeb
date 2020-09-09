import { Injectable, EventEmitter } from '@angular/core';
import { Subscription } from 'rxjs/internal/Subscription';    


@Injectable({
  providedIn: 'root'
})
export class EventEmitterService {
  invokeSearchFunction = new EventEmitter();
  invokeChangeTypeFunction = new EventEmitter();

  subsVar: Subscription;
  constructor() { }

  onSearch(text) {
    this.invokeSearchFunction.emit(text);
  }

  onTypeChange(type, name) {    
    this.invokeChangeTypeFunction.emit({type, name});
  }
}
