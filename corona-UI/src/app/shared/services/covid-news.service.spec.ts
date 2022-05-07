import { TestBed } from '@angular/core/testing';

import { CovidNewsService } from './covid-news.service';

describe('CovidNewsService', () => {
  let service: CovidNewsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CovidNewsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
