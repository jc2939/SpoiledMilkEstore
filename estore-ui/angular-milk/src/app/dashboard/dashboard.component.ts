import { Component, OnInit } from '@angular/core';
import { Milk } from '../milk';
import { MilkService } from '../milk.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
  milks: Milk[] = [];

  constructor(private MilkService: MilkService) { }

  ngOnInit(): void {
    this.getMilks();
  }

  getMilks(): void {
    this.MilkService.getMilks()
      .subscribe(milks => this.milks = milks.slice(1, 5));
  }


}