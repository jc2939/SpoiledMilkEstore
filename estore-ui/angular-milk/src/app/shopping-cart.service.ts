import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { ShoppingCart } from './shoppingCart';
import { Milk } from './milk';

@Injectable({providedIn: 'root'})
export class ShoppingCartService {
  private milksUrl = 'http://localhost:8080/cart'
  
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  constructor(private http: HttpClient) { }

  /** GET milks from the server */
  getShoppingCarts(): Observable<ShoppingCart[]> {
    return this.http.get<ShoppingCart[]>(this.milksUrl)
      .pipe(
        catchError(this.handleError<ShoppingCart[]>('getShoppingCarts', []))
      );
  }

  addMilk(milk: Milk, userName: String): Observable<ShoppingCart> {
    return this.http.post<ShoppingCart>(this.milksUrl, milk, this.httpOptions).pipe(
      catchError(this.handleError<ShoppingCart>('addMilk'))
    );
  }

  /** DELETE: delete the milk from the server */
  decrementMilk(milk: Milk, userName: String): Observable<ShoppingCart> {
    const url = `${this.milksUrl}/${userName}`;

    return this.http.delete<ShoppingCart>(url, this.httpOptions).pipe(
      catchError(this.handleError<ShoppingCart>('decrementMilk'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }


}
