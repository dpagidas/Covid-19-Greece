import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CasesTableListComponent } from './cases-table-list.component';

describe('CasesTableListComponent', () => {
  let component: CasesTableListComponent;
  let fixture: ComponentFixture<CasesTableListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CasesTableListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CasesTableListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
