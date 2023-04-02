import { Injectable } from '@angular/core';
import { Subject, BehaviorSubject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ShoppingCartDataService {

  constructor() { }

  public username: string = "";
  public subject = new Subject<string>();
  private messageSource = new  BehaviorSubject(this.username);
  currentMessage = this.messageSource.asObservable();

  changeMessage(message: string) {
    this.messageSource.next(message)
  }
}
