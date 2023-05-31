import { TestBed } from '@angular/core/testing';

import { InterceptorAuth } from './interceptor.interceptor';

describe('InterceptorAuth', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      InterceptorAuth
      ]
  }));

  it('should be created', () => {
    const interceptor: InterceptorAuth = TestBed.inject(InterceptorAuth);
    expect(interceptor).toBeTruthy();
  });
});
