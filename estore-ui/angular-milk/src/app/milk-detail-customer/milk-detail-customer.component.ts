import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Milk } from '../milk';
import { MilkService } from '../milk.service';

@Component({
  selector: 'app-milk-detail',
  templateUrl: './milk-detail-customer.component.html',
  styleUrls: [ './milk-detail-customer.component.css' ]
})
export class MilkDetailCustomerComponent implements OnInit {
  milk: Milk | undefined;

  constructor(
    private route: ActivatedRoute,
    private MilkService: MilkService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getMilk();
  }

  getMilk(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.MilkService.getMilk(id)
      .subscribe(milk => this.milk = milk);
  }

  goBack(): void {
    this.location.back();
  }


}