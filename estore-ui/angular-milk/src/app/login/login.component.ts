import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Login } from '../login';
import { ShoppingCartDataService } from '../shopping-cart-data.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  ngOnInit(): void {
  }

  async login() {
    const login = {"username": this.username!, "password": this.password!} as Login
    if (await this.LoginService.login(login)) {
      if (this.username === 'admin'){
        this._router.navigateByUrl("/milks")
      } else {
        this._router.navigateByUrl("/dashboard")
        this.ShoppingCartDataService.changeMessage(this.username!);
      }
    } else {
      this.username = "";
      this.error = "Bad login."
    }
  }

  resetPassword() {
    this.LoginService.resetPassword(this.username!)
  }

  constructor(private LoginService: LoginService, private _router: Router, private ShoppingCartDataService: ShoppingCartDataService) {}

  username: string | undefined;
  password: string | undefined;
  error: string = "";
}
