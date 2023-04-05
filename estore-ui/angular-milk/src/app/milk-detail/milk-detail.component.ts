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
  imageType: string | undefined;
  base64code: string | undefined;

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

  fileSelected(event: any){
    let me = this;
    let file = event.target!.files[0];
    me.imageType = event.target!.files[0].type;
    console.log(this.imageType);
    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
      me.base64code = reader.result!.toString().split(',')[1];
    };
    reader.onerror = function (error) {
      console.log('Error: ', error);
    };
  }

  clearRatings(): void {
    if (this.milk) {
      this.milk.calcRating = 0;
      this.milk.rating = [];
    }
  }

  save(): void {
    if (this.milk) {
      if (this.base64code){
        this.milk!.imageUrl = "data:" + this.imageType + ";base64," + this.base64code;
      }  
      this.MilkService.updateMilk(this.milk)
        .subscribe(() => this.goBack());
    }
  }
}