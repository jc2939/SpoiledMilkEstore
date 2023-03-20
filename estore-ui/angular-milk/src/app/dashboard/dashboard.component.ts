import { Component, OnInit } from '@angular/core';
import { Milk } from '../milk';
import { MilkService } from '../milk.service';
import { ShoppingCartService } from '../shopping-cart.service';
import { ShoppingCart } from '../shoppingCart';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
  milks: Milk[] = [];
  shoppingCart: ShoppingCart | undefined;

  constructor(private MilkService: MilkService, private ShoppingCartService: ShoppingCartService) { }

  ngOnInit(): void {
    this.getMilks();
    this.getShoppingCart();
  }

  getShoppingCart(): void {
    this.ShoppingCartService.getShoppingCart("Jeremy")
    .subscribe(shoppingCart => this.shoppingCart = shoppingCart);
  }

  getMilks(): void {
    this.MilkService.getMilks()
      .subscribe(milks => this.milks = milks);
  }

  addOne(milk: Milk, userName: String): void {
    milk.quantity = milk.quantity - 1;
    this.MilkService.updateMilk(milk).subscribe();
    
    this.ShoppingCartService.incrementMilk(milk, userName).subscribe(() => {
      const index = this.shoppingCart?.milksInCart.findIndex(item => item.id === milk.id);
      if (this.shoppingCart && this.shoppingCart.milksInCartQuantity && index !== undefined && index !== -1) {
        this.shoppingCart.milksInCartQuantity[index] += 1;
      }
    });
    this.reloadPage();
  }

  reloadPage() {
    window.location.reload();
  }

}