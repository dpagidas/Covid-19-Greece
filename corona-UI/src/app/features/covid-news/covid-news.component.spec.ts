import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CovidNewsComponent } from './covid-news.component';

describe('CovidNewsComponent', () => {
  let component: CovidNewsComponent;
  let fixture: ComponentFixture<CovidNewsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CovidNewsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CovidNewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
