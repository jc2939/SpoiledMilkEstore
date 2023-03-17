import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MilkDetailCustomerComponent } from './milk-detail-customer.component';

describe('MilkDetailCustomerComponent', () => {
  let component: MilkDetailCustomerComponent;
  let fixture: ComponentFixture<MilkDetailCustomerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MilkDetailCustomerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MilkDetailCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
