import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MilkSearchComponent } from './milk-search.component';

describe('MilkSearchComponent', () => {
  let component: MilkSearchComponent;
  let fixture: ComponentFixture<MilkSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MilkSearchComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MilkSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
