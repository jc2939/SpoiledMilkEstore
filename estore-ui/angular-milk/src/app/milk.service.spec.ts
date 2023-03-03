import { TestBed } from '@angular/core/testing';

import { MilkService } from './milk.service';

describe('MilkService', () => {
  let service: MilkService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MilkService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
