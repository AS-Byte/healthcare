import { TestBed } from '@angular/core/testing';

import { TypeSoinsService } from './type-soins.service';

describe('TypeSoinsService', () => {
  let service: TypeSoinsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TypeSoinsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
