import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CasesBarChartComponent } from './cases-bar-chart.component';

describe('CasesBarChartComponent', () => {
  let component: CasesBarChartComponent;
  let fixture: ComponentFixture<CasesBarChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CasesBarChartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CasesBarChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
