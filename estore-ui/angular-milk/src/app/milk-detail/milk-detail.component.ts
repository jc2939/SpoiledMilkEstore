import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Milk } from '../milk';
import { MilkService } from '../milk.service';

@Component({
  selector: 'app-milk-detail',
  templateUrl: './milk-detail.component.html',
  styleUrls: [ './milk-detail.component.css' ]
})
export class MilkDetailComponent implements OnInit {
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

  save(): void {
    if (this.milk) {
      this.MilkService.updateMilk(this.milk)
        .subscribe(() => this.goBack());
    }
  }
}