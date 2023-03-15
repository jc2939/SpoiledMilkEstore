import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Milk } from './milk';


@Injectable({ providedIn: 'root' })
export class MilkService {

  private milksUrl = 'http://localhost:8080/milks'
  
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient) { }

  /** GET milks from the server */
  getMilks(): Observable<Milk[]> {
    return this.http.get<Milk[]>(this.milksUrl)
      .pipe(
        catchError(this.handleError<Milk[]>('getMilks', []))
      );
  }

  /** GET milk by id. Return `undefined` when id not found */
  getMilkNo404<Data>(id: number): Observable<Milk> {
    const url = `${this.milksUrl}/?id=${id}`;
    return this.http.get<Milk[]>(url)
      .pipe(
        map(milks => milks[0]), // returns a {0|1} element array
        catchError(this.handleError<Milk>(`getMilk id=${id}`))
      );
  }

  /** GET milk by id. Will 404 if id not found */
  getMilk(id: number): Observable<Milk> {
    const url = `${this.milksUrl}/${id}`;
    return this.http.get<Milk>(url).pipe(
      catchError(this.handleError<Milk>(`getMilk id=${id}`))
    );
  }

  /* GET milks whose name contains search term */
  searchMilks(term: string): Observable<Milk[]> {
    if (!term.trim()) {
      // if not search term, return empty milk array.
      return of([]);
    }
    return this.http.get<Milk[]>(`${this.milksUrl}/?type=${term}`).pipe(
      catchError(this.handleError<Milk[]>('searchMilks', []))
    );
  }

  //////// Save methods //////////

  /** POST: add a new milk to the server */
  addMilk(milk: Milk): Observable<Milk> {
    return this.http.post<Milk>(this.milksUrl, milk, this.httpOptions).pipe(
      catchError(this.handleError<Milk>('addMilk'))
    );
  }

  /** DELETE: delete the milk from the server */
  deleteMilk(id: number): Observable<Milk> {
    const url = `${this.milksUrl}/${id}`;

    return this.http.delete<Milk>(url, this.httpOptions).pipe(
      catchError(this.handleError<Milk>('deleteMilk'))
    );
  }

  /** PUT: update the milk on the server */
  updateMilk(milk: Milk): Observable<any> {
    return this.http.put(this.milksUrl, milk, this.httpOptions).pipe(
      catchError(this.handleError<any>('updateMilk'))
    );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   *
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}