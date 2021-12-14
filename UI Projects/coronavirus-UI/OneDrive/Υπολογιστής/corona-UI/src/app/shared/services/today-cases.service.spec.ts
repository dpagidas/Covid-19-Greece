import { TestBed } from '@angular/core/testing';

import { TodayCasesService } from './today-cases.service';

describe('TodayCasesService', () => {
  let service: TodayCasesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TodayCasesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
