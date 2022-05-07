import { TestBed } from '@angular/core/testing';

import { RegionCasesHistoryService } from './region-cases-history.service';

describe('RegionCasesHistoryService', () => {
  let service: RegionCasesHistoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegionCasesHistoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
