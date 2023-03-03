import { Component, OnInit } from '@angular/core';

import { Milk } from '../milk';
import { MilkService } from '../milk.service';

@Component({
  selector: 'app-heroes',
  templateUrl: './milks.component.html',
  styleUrls: ['./milks.component.css']
})
export class MilksComponent implements OnInit {
  milks: Milk[] = [];

  constructor(private MilkService: MilkService) { }

  ngOnInit(): void {
    this.getMilks();
  }

  getMilks(): void {
    this.MilkService.getMilks()
    .subscribe(heroes => this.milks = heroes);
  }

  add(name: string): void {
    name = name.trim();
    if (!name) { return; }
    this.MilkService.addMilk({ name } as Milk)
      .subscribe(milk => {
        this.milks.push(milk);
      });
  }

  delete(milk: Milk): void {
    this.milks = this.milks.filter(m => m !== milk);
    this.MilkService.deleteMilk(milk.id).subscribe();
  }

}