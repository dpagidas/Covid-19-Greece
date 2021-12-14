import { TestBed } from '@angular/core/testing';

import { GenericRestService } from './generic-rest.service';

describe('GenericRestService', () => {
  let service: GenericRestService<any>;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GenericRestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
