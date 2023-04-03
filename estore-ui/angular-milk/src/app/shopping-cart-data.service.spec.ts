import { TestBed } from '@angular/core/testing';

import { ShoppingCartDataService } from './shopping-cart-data.service';

describe('ShoppingCartDataService', () => {
  let service: ShoppingCartDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShoppingCartDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
