import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Login } from '../login';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  ngOnInit(): void {
  }

  async login() {
    /*const login: Login = {
      username: this.username!,
      password: this.password!
    }
    */
    const login = {"username": this.username!, "password": this.password!} as Login
    if (await this.LoginService.login(login)) {
      this._router.navigateByUrl("/dashboard")
    } else {
      this.username = "";
      this.error = "Bad login."
    }
  }

  resetPassword() {
    this.LoginService.resetPassword(this.username!)
  }

  constructor(private LoginService: LoginService, private _router: Router) {}

  username: string | undefined;
  password: string | undefined;
  error: string = "";
}
