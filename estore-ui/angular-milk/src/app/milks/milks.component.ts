import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';

import { Milk } from '../milk';
import { MilkService } from '../milk.service';

@Component({
  selector: 'app-milks',
  templateUrl: './milks.component.html',
  styleUrls: ['./milks.component.css']
})
export class MilksComponent implements OnInit {
  milks: Milk[] = [];

  constructor(private MilkService: MilkService, private loginService: LoginService, private _router: Router) { }

  ngOnInit(): void {
    this.getMilks();
  }

  getMilks(): void {
    this.MilkService.getMilks()
    .subscribe(milks => this.milks = milks);
  }

  add(type: string, flavor: string, volume: number, quantity: number, price: number, imageUrl: string): void {
    type = type.trim();
    flavor = flavor.trim();
    if (!type) { return; }
    this.MilkService.addMilk({ type, flavor, volume, quantity, price, imageUrl } as Milk)
      .subscribe(milk => {
        this.milks.push(milk);
      });
  }

  delete(milk: Milk): void {
    this.milks = this.milks.filter(m => m !== milk);
    this.MilkService.deleteMilk(milk.id).subscribe();
  }

  stringToInt(input: string): number
  {
    var number = Number(input);
    return number;
  }

  fileName(): string
  {
    var file = (document.getElementById("milk-image") as HTMLInputElement)!;
    var path = file.files![0];
    var filename = path.name;
    var pathName = "../assets/images/" + filename
    return pathName;
  }

  logout() {
    this.loginService.logout()
    this._router.navigateByUrl("/login")
  }


}