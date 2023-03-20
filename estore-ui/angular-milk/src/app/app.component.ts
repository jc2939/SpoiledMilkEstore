import { Component } from '@angular/core';
import { LoginService } from './login.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Welcome to our Spoiled Milk E-store!';
  //myimage:string = "assets/images/glass-o-milk.jpg";

  constructor(private loginService: LoginService, private _router: Router) { }

  logout() {
    this.loginService.logout()
    this._router.navigateByUrl("/login")
  }
}

