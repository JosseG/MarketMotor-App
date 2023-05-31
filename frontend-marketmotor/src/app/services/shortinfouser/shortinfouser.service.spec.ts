import { TestBed } from '@angular/core/testing';

import { ShortinfouserService } from './shortinfouser.service';

describe('ShortinfouserService', () => {
  let service: ShortinfouserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShortinfouserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
