import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';


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

  async login(username: string, password: string): Promise<boolean> {
    var test = (await firstValueFrom(this.http.post(this.loginUrl, [username, password], this.httpOptions))).toString();
    if (test != "") {
      this.username = test;
      return true;
    }
    return false;
  }

  resetPassword(username: string) {
    console.log(username)
    this.http.delete(`${this.loginUrl}/${username}`, this.httpOptions)
  }

  logout() {
    this.username = undefined
  }
}
