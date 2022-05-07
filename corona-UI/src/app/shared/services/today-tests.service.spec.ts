import { TestBed } from '@angular/core/testing';

import { TodayTestsService } from './today-tests.service';

describe('TodayTestsService', () => {
  let service: TodayTestsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TodayTestsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
