import { Component, OnInit } from '@angular/core';
import { Milk } from '../milk';
import { MilkService } from '../milk.service';
import { ShoppingCartService } from '../shopping-cart.service';
import { ShoppingCart } from '../shoppingCart';


@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit{
  shoppingCarts: ShoppingCart[] = [];

  constructor(private ShoppingCartService: ShoppingCartService) { }

  ngOnInit(): void {
    this.getShoppingCarts();
    //console.log("Test");

  }

  getShoppingCarts(): void {
    this.ShoppingCartService.getShoppingCarts()
    .subscribe(shoppingCarts => this.shoppingCarts = shoppingCarts);
  }

  purchase(): void{

  }

  addOne(cart: ShoppingCart): void {
    // this.milks = this.milks.filter(m => m !== milk);
    // this.MilkService.deleteMilk(milk.id).subscribe();
  }
  deleteOne(cart: ShoppingCart): void {
    // this.milks = this.milks.filter(m => m !== milk);
    // this.MilkService.deleteMilk(milk.id).subscribe();
  }

  // properImage(milk: Milk): void{
  //   var url = milk.imageUrl;

  // }
  stringToInt(input: string): number
  {
    var number = Number(input);
    return number;
  }


  // getMilks(): void {
  //   this.MilkService.getMilks()
  //   .subscribe(milks => this.milks = milks);
  // }

  // add(type: string, flavor: string, volume: number, quantity: number, price: number): void {
  //   type = type.trim();
  //   flavor = flavor.trim();
  //   if (!type) { return; }
  //   this.MilkService.addMilk({ type, flavor, volume, quantity, price } as Milk)
  //     .subscribe(milk => {
  //       this.milks.push(milk);
  //     });
  // }

}
