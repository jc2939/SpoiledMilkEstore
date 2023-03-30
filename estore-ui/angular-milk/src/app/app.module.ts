import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MilkDetailComponent } from './milk-detail/milk-detail.component';
import { MilksComponent } from './milks/milks.component';
import { MilkSearchComponent } from './milk-search/milk-search.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { LoginComponent } from './login/login.component';
import { MilkDetailCustomerComponent } from './milk-detail-customer/milk-detail-customer.component';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    MilksComponent,
    MilkDetailComponent,
    MilkDetailCustomerComponent,
    MilkSearchComponent,
    ShoppingCartComponent,
    LoginComponent,
    MilkDetailCustomerComponent
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }