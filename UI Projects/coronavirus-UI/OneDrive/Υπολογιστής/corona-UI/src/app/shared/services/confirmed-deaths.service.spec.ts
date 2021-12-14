import { TestBed } from '@angular/core/testing';

import { ConfirmedDeathsService } from './confirmed-deaths.service';

describe('ConfirmedDeathsService', () => {
  let service: ConfirmedDeathsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConfirmedDeathsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
