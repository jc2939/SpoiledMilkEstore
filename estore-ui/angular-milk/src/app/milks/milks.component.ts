import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { FormsModule } from '@angular/forms';
import { Milk } from '../milk';
import { MilkService } from '../milk.service';
import { Observable, Subscriber } from 'rxjs';

@Component({
  selector: 'app-milks',
  templateUrl: './milks.component.html',
  styleUrls: ['./milks.component.css']
})
export class MilksComponent implements OnInit {
  milks: Milk[] = [];
  imageType: string | undefined;
  base64code: string | undefined;

  constructor(private MilkService: MilkService, private loginService: LoginService, private _router: Router) { }

  ngOnInit(): void {
    this.getMilks();
  }

  getMilks(): void {
    this.MilkService.getMilks()
    .subscribe(milks => this.milks = milks);
  }

  fileSelected(event: any){
    let me = this;
    let file = event.target!.files[0];
    me.imageType = event.target!.files[0].type;
    console.log(this.imageType);
    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
      me.base64code = reader.result!.toString().split(',')[1];
    };
    reader.onerror = function (error) {
      console.log('Error: ', error);
    };
  }

  add(type: string, flavor: string, volume: number, quantity: number, price: number, rating: number[], calcRating: number): void {
    type = type.trim();
    flavor = flavor.trim();
    if (!type) { return; }
    const milk: Milk = {
      id: 0,
      type: type,
      flavor: flavor,
      volume: volume,
      quantity: quantity,
      price: price,
      rating: rating,
      calcRating: calcRating,
      imageUrl: "data:" + this.imageType + ";base64," + this.base64code // use base64code as the imageUrl value
    };
    this.MilkService.addMilk(milk)
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

  logout() {
    this.loginService.logout()
    this._router.navigateByUrl("/login")
  }

}