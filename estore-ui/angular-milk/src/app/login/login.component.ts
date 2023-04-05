import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Login } from '../login';
import { ShoppingCartService } from '../shopping-cart.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  shoppingCart: any;

  ngOnInit(): void {
  }

  async login() {
    const login = {"username": this.username!, "password": this.password!} as Login
    if (await this.LoginService.login(login)) {
      if (this.username === 'admin'){
        this._router.navigateByUrl("/milks")
      } else {
        this._router.navigateByUrl("/dashboard")
        localStorage.setItem("username", this.username!);
      }
    } else {
      this.username = "";
      this.password = "";
      this.error = "Bad login."
    }
  }

  async signup() {
    const login = {"username": this.username!, "password": this.password!} as Login
    if (await this.LoginService.signup(login)) {
      this.ShoppingCartService.createNewCart(login.username).subscribe(shoppingCart => this.shoppingCart = shoppingCart);
      localStorage.setItem("username", this.username!);
      this._router.navigateByUrl("/dashboard")
    } else {
      this.username = "";
      this.password = "";
      this.error = "Bad signup."
    }
  }

  async resetPassword() {
    if (await this.LoginService.resetPassword(this.username!) != null) {
      this.ShoppingCartService.deleteShoppingCart(this.username!).subscribe(shoppingCart => this.shoppingCart = shoppingCart);
      this.username = "";
      this.password = "";
      this.error = "Password reset!"
    }
  }

  constructor(private LoginService: LoginService, private _router: Router,private ShoppingCartService: ShoppingCartService) {}

  username: string | undefined;
  password: string | undefined;
  error: string = "Login with a username and password";
}
