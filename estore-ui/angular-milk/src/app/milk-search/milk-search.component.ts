import { Component, OnInit } from '@angular/core';

import { Observable, Subject } from 'rxjs';

import {
   debounceTime, distinctUntilChanged, switchMap
 } from 'rxjs/operators';

import { Milk } from '../milk';
import { MilkService } from '../milk.service';

@Component({
  selector: 'app-milk-search',
  templateUrl: './milk-search.component.html',
  styleUrls: [ './milk-search.component.css' ]
})
export class MilkSearchComponent implements OnInit {
  milks$!: Observable<Milk[]>;
  private searchTerms = new Subject<string>();

  constructor(private MilkService: MilkService) {}

  // Push a search term into the observable stream.
  search(term: string): void {
    term = term.toLowerCase();
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.milks$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.MilkService.searchMilks(term)),
    );
  }
}