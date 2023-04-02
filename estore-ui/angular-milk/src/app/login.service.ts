import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';

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
    let res: boolean = await firstValueFrom(this.http.post<boolean>(`${this.loginUrl}/login`, login, this.httpOptions))
    return res;
  }

  async signup(login: Login): Promise<boolean> {
    console.log(`${this.loginUrl}/signup`)
    let res: boolean = await firstValueFrom(this.http.post<boolean>(`${this.loginUrl}/signup`, login, this.httpOptions))
    return res;
  }

  async resetPassword(username: string) {
    console.log(username)
    let res: string = await firstValueFrom(this.http.delete<string>(`${this.loginUrl}/${username}`, this.httpOptions))
  }

  logout() {
    this.username = undefined
  }
}
