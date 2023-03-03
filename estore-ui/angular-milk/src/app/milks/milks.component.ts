import { Component, OnInit } from '@angular/core';

import { Milk } from '../milk';
import { HeroService } from '../milk.service';

@Component({
  selector: 'app-heroes',
  templateUrl: './milks.component.html',
  styleUrls: ['./milks.component.css']
})
export class HeroesComponent implements OnInit {
  milks: Milk[] = [];

  constructor(private heroService: HeroService) { }

  ngOnInit(): void {
    this.getHeroes();
  }

  getHeroes(): void {
    this.heroService.getHeroes()
    .subscribe(heroes => this.milks = heroes);
  }

  add(name: string): void {
    name = name.trim();
    if (!name) { return; }
    this.heroService.addHero({ name } as Milk)
      .subscribe(milk => {
        this.milks.push(milk);
      });
  }

  delete(milk: Milk): void {
    this.milks = this.milks.filter(m => m !== milk);
    this.heroService.deleteHero(milk.id).subscribe();
  }

}