import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VaccinationsTableRegionListComponent } from './vaccinations-table-region-list.component';

describe('VaccinationsTableRegionListComponent', () => {
  let component: VaccinationsTableRegionListComponent;
  let fixture: ComponentFixture<VaccinationsTableRegionListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VaccinationsTableRegionListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VaccinationsTableRegionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
