import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MilkDetailComponent } from './milk-detail.component';

describe('MilkDetailComponent', () => {
  let component: MilkDetailComponent;
  let fixture: ComponentFixture<MilkDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MilkDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MilkDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
