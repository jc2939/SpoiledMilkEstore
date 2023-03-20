import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  ngOnInit(): void {
  }

  async login() {
    if (await this.LoginService.login(this.username!, this.password!)) {
      this._router.navigateByUrl("/dashboard")
    }
  }

  constructor(private LoginService: LoginService, private _router: Router) {}

  username: string | undefined;
  password: string | undefined;
  error: string | undefined
}
