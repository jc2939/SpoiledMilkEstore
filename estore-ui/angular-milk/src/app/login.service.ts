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
    let res: boolean = await firstValueFrom(this.http.post<boolean>(this.loginUrl, login, this.httpOptions))
    console.log(`res: ${res}`);
    return res;
    // var test = (await firstValueFrom(this.http.post(this.loginUrl, login, this.httpOptions))).toString();
    // if (test != "") {
    //   this.username = test;
    //   return true;
    // }
  }

  resetPassword(username: string) {
    console.log(username)
    this.http.delete(`${this.loginUrl}/${username}`, this.httpOptions)
  }

  logout() {
    this.username = undefined
  }
}
