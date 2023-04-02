import { Component, OnInit } from '@angular/core';
import { ShoppingCartService } from '../shopping-cart.service';
import { MilkService } from '../milk.service';
import { ShoppingCart } from '../shoppingCart';
import { Milk } from '../milk';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { ShoppingCartDataService } from '../shopping-cart-data.service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit{
  shoppingCart: ShoppingCart | undefined;
  private currUsername: string | undefined

  constructor(private ShoppingCartService: ShoppingCartService, private ShoppingCartDataService: ShoppingCartDataService, private MilkService: MilkService,
    private loginService: LoginService, private _router: Router) { }

  ngOnInit(): void {
    this.getShoppingCart();
  }



  getShoppingCart(): void {
    this.ShoppingCartDataService.currentMessage.subscribe(message => (this.currUsername = message));
     this.ShoppingCartService.getShoppingCart(this.currUsername!)
     .subscribe(shoppingCart => this.shoppingCart = shoppingCart);
  }

  purchase(): void{

  }

  addOne(milk: Milk, username: String): void {
    milk.quantity = milk.quantity - 1;
    this.MilkService.updateMilk(milk).subscribe();

    this.ShoppingCartService.incrementMilk(milk, username).subscribe(() => {
      const index = this.shoppingCart?.milksInCart.findIndex(item => item.id === milk.id);
      if (this.shoppingCart && this.shoppingCart.milksInCartQuantity && index !== undefined && index !== -1) {
        this.shoppingCart.milksInCartQuantity[index] += 1;
      }
    });
  }

  

  deleteOne(milk: Milk, id: number, username: String): void {
    milk.quantity = milk.quantity + 1;
    this.MilkService.updateMilk(milk).subscribe();

    this.ShoppingCartService.decrementMilk(id, username).subscribe(() => {
      const index = this.shoppingCart?.milksInCart.findIndex(item => item.id === id);
      if (this.shoppingCart && this.shoppingCart.milksInCartQuantity && index !== undefined && index !== -1) {
        this.shoppingCart.milksInCartQuantity[index] -= 1;
        if (this.shoppingCart?.milksInCartQuantity[index] <= 0)
          this.reloadPage();
      }
    });
    
  }

  reloadPage() {
    window.location.reload();
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
