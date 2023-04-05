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

  rateMilk(rating: number): void {
    if (this.milk != undefined) {
      var newRatings: number[] = this.milk.rating;
      var len: number;
      var sum: number = 0;
      if (newRatings == null) {
        newRatings = [rating];
        len = 1;
      } else {
        len = newRatings.push(rating);
      }
      this.milk.rating = newRatings;
      for (let i = 0; i < len; i++) {
        sum += this.milk.rating[i];
      }
      this.milk.calcRating = Math.round((sum/len) * 10) / 10;
      this.MilkService.updateMilk(this.milk)
        .subscribe(milk => this.milk = milk);
      //window.location.reload();
    }
  
  }


}