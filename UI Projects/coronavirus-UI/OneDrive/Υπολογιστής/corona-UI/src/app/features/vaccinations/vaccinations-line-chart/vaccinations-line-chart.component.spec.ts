import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VaccinationsLineChartComponent } from './vaccinations-line-chart.component';

describe('VaccinationsLineChartComponent', () => {
  let component: VaccinationsLineChartComponent;
  let fixture: ComponentFixture<VaccinationsLineChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VaccinationsLineChartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VaccinationsLineChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
