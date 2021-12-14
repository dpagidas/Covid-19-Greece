import { TestBed } from '@angular/core/testing';

import { IntensiveCareService } from './intensive-care.service';

describe('IntensiveCareService', () => {
  let service: IntensiveCareService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IntensiveCareService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
