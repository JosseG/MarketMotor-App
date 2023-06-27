import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportetemplateventaComponent } from './reportetemplateventa.component';

describe('ReportetemplateventaComponent', () => {
  let component: ReportetemplateventaComponent;
  let fixture: ComponentFixture<ReportetemplateventaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReportetemplateventaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReportetemplateventaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
