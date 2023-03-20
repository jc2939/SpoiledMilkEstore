import { Component, OnInit } from '@angular/core';
import { ShoppingCartService } from '../shopping-cart.service';
import { ShoppingCart } from '../shoppingCart';
import { Milk } from '../milk';

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
  }

  getShoppingCart(): void {
    this.ShoppingCartService.getShoppingCart("Jeremy")
    .subscribe(shoppingCart => this.shoppingCart = shoppingCart);
  }

  purchase(): void{

  }

  addOne(milk: Milk, username: String): void {
    this.ShoppingCartService.incrementMilk(milk, username).subscribe(() => {
      const index = this.shoppingCart?.milksInCart.findIndex(item => item.id === milk.id);
      if (this.shoppingCart && this.shoppingCart.milksInCartQuantity && index !== undefined && index !== -1) {
        this.shoppingCart.milksInCartQuantity[index] += 1;
      }
    });
    this.reloadPage();
  }

  deleteOne(id: number, username: String): void {
    this.ShoppingCartService.decrementMilk(id, username).subscribe(() => {
      const index = this.shoppingCart?.milksInCart.findIndex(item => item.id === id);
      if (this.shoppingCart && this.shoppingCart.milksInCartQuantity && index !== undefined && index !== -1) {
        this.shoppingCart.milksInCartQuantity[index] -= 1;
      }
    });
    this.reloadPage();
  }

  reloadPage() {
    window.location.reload();
 }

  stringToInt(input: string): number
  {
    var number = Number(input);
    return number;
  }

}
