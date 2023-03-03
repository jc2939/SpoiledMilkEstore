import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Milk } from '../milk';
import { HeroService } from '../milk.service';

@Component({
  selector: 'app-milk-detail',
  templateUrl: './milk-detail.component.html',
  styleUrls: [ './milk-detail.component.css' ]
})
export class HeroDetailComponent implements OnInit {
  milk: Milk | undefined;

  constructor(
    private route: ActivatedRoute,
    private heroService: HeroService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getHero();
  }

  getHero(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.heroService.getHero(id)
      .subscribe(milk => this.milk = milk);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.milk) {
      this.heroService.updateHero(this.milk)
        .subscribe(() => this.goBack());
    }
  }
}