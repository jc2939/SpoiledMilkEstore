import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


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
    var result: string = "";
    this.http.post(this.loginUrl, [username, password], this.httpOptions).subscribe(username => { 
      console.log(username)
      result = JSON.parse(JSON.stringify(username)).username; 
      console.log(result);
    })
    await new Promise(f => setTimeout(f, 1000));
    if (result != "") {
      this.username == result
      console.log("true")
      return true;
    }
    console.log("false")
    return false;
  }

  logout() {
    this.username = undefined
  }
}
