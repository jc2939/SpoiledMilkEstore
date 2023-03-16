import { Component, OnInit } from '@angular/core';
import { ShoppingCartService } from '../shopping-cart.service';
import { ShoppingCart } from '../shoppingCart';


@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit{
  shoppingCart: ShoppingCart | undefined;

  constructor(private ShoppingCartService: ShoppingCartService) { }

  ngOnInit(): void {
    this.getShoppingCart();
    //console.log(this.shoppingCarts);
    //console.log("Test");
  }

  getShoppingCart(): void {
    this.ShoppingCartService.getShoppingCart("Yaro")
    .subscribe(shoppingCart => this.shoppingCart = shoppingCart);
  }

  purchase(): void{

  }
  // cart: ShoppingCart
  addOne(): void {
    // this.milks = this.milks.filter(m => m !== milk);
    // this.MilkService.deleteMilk(milk.id).subscribe();
  }
  deleteOne(): void {
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
