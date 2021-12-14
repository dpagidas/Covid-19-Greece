import { TestBed } from '@angular/core/testing';

import { GenderAgeDistributionService } from './gender-age-distribution.service';

describe('GenderAgeDistributionService', () => {
  let service: GenderAgeDistributionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GenderAgeDistributionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
