import { Component, OnInit } from '@angular/core';
import { ShoppingCartService } from '../shopping-cart.service';
import { MilkService } from '../milk.service';
import { ShoppingCart } from '../shoppingCart';
import { Milk } from '../milk';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';


@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit{
  // shoppingCart: ShoppingCart | undefined;
  shoppingCart: any;
  private currUsername: string | undefined
  deliveryAddress: string = '';
  total: number = 0;

  constructor(private ShoppingCartService: ShoppingCartService, private MilkService: MilkService,
    private loginService: LoginService, private _router: Router) { }

  ngOnInit(): void {
    this.getShoppingCart();
    this.calculateTotal();
  }

  getShoppingCart(): void {
    this.currUsername = localStorage.getItem("username")!;
    this.ShoppingCartService.getShoppingCart(this.currUsername!)
     .subscribe(shoppingCart => this.shoppingCart = shoppingCart);
  }

  calculateTotal(): number {
    let total = 0;
    for (let i = 0; i < this.shoppingCart.milksInCart.length; i++) {
      total += this.shoppingCart.milksInCart[i].price * this.shoppingCart.milksInCartQuantity[i];
    }
    return parseFloat(total.toFixed(2));
  }

  purchase() {
    console.log('Delivery address:', this.deliveryAddress);
    console.log('Purchase total:', this.total);
    const totalStr = this.total.toFixed(2);
    document.getElementById('purchase')!.innerHTML = 'Purchase total: $' + totalStr;
    this.ShoppingCartService.emptyShoppingCart(this.currUsername!)
     .subscribe(shoppingCart => this.shoppingCart = shoppingCart);
    }
  

  addOne(milk: Milk, username: String): void {
    milk.quantity = milk.quantity - 1;
    this.MilkService.updateMilk(milk).subscribe();

    this.ShoppingCartService.incrementMilk(milk, username).subscribe(() => {
      const index = this.shoppingCart?.milksInCart.findIndex((item: { id: number; }) => item.id === milk.id);
      if (this.shoppingCart && this.shoppingCart.milksInCartQuantity && index !== undefined && index !== -1) {
        this.shoppingCart.milksInCartQuantity[index] += 1;
      }
    });
  }

  deleteOne(milk: Milk, id: number, username: String): void {
    milk.quantity = milk.quantity + 1;
    this.MilkService.updateMilk(milk).subscribe();

    this.ShoppingCartService.decrementMilk(id, username).subscribe(() => {
      const index = this.shoppingCart?.milksInCart.findIndex((item: { id: number; }) => item.id === id);
      if (this.shoppingCart && this.shoppingCart.milksInCartQuantity && index !== undefined && index !== -1) {
        this.shoppingCart.milksInCartQuantity[index] -= 1;
        if (this.shoppingCart?.milksInCartQuantity[index] <= 0){
          this.getShoppingCart();
        }  
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
