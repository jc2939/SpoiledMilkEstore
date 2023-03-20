import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent } from './dashboard/dashboard.component';
import { MilksComponent } from './milks/milks.component';
import { MilkDetailComponent } from './milk-detail/milk-detail.component';
import { MilkDetailCustomerComponent } from './milk-detail-customer/milk-detail-customer.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'detail/:id', component: MilkDetailComponent },
  { path: 'detail-customer/:id', component: MilkDetailCustomerComponent },
  { path: 'milks', component: MilksComponent },
  { path: 'shopping-cart', component: ShoppingCartComponent },
  { path: 'login', component: LoginComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}