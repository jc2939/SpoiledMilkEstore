import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, firstValueFrom, Observable, of } from 'rxjs';

import { Login } from './login';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loginUrl = 'http://localhost:8080/login'
  private username: string | undefined
  
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };


  constructor(private http: HttpClient) { }

  async login(login: Login): Promise<boolean> {
    let res: boolean = await firstValueFrom<boolean>(this.http.post<boolean>(`${this.loginUrl}/login`, login, this.httpOptions).pipe(catchError(this.handleError<boolean>())))
    return res;
  }

  async signup(login: Login): Promise<boolean> {
    let res: boolean = await firstValueFrom<boolean>(this.http.post<boolean>(`${this.loginUrl}/signup`, login, this.httpOptions).pipe(catchError(this.handleError<boolean>())))
    return res;
  }

  async resetPassword(username: string): Promise<boolean> {
    console.log(username)
    let res: boolean = await firstValueFrom<boolean>(this.http.delete<boolean>(`${this.loginUrl}/${username}`, this.httpOptions).pipe(catchError(this.handleError<boolean>())))
    console.log(res)
    return res;
  }

  logout() {
    this.username = undefined
  }

  private handleError<T>(result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
