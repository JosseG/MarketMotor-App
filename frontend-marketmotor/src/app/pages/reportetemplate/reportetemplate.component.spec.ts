import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportetemplateComponent } from './reportetemplate.component';

describe('ReportetemplateComponent', () => {
  let component: ReportetemplateComponent;
  let fixture: ComponentFixture<ReportetemplateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReportetemplateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReportetemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
